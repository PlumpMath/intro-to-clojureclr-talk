; the first two assemblies are required for using the mono version of 
; the postgresql data provider
(assembly-load-from ".\\deps\\mono.security.dll")
(assembly-load-from ".\\deps\\npgsql.dll")
(System.Reflection.Assembly/LoadWithPartialName "System.Data")
(System.Reflection.Assembly/LoadWithPartialName "System.Configuration")

(ns db.queries
 ;(:use db.core)
 (:import (Npgsql NpgsqlConnection NpgsqlCommand)
          (System.Data DataTable))
 (:gen-class
  :methods [ #^{:static true} [getPlayer [System.String] System.String]]))
 
(def ^{:private true}
  conn-str (.Get System.Configuration.ConfigurationManager/AppSettings "DbConnStr"))
;  conn-str "Server=127.0.0.1;Port=5432;Database=hockeydb;User Id=postgres;Password=postgres;")

(def ^{:private true} 
  dbconn (Npgsql.NpgsqlConnection. conn-str))

(defn resultset-seq 
  "Creates and returns a lazy sequence of maps corresponding to
   the rows in the System.Data.DataTable.  Based on clojure.core/resultset-seq
   but it respects the current naming strategy. Duplicate column names are
   made unique by appending _N before applying the naming strategy (where
   N is a unique integer)."
  [^DataTable dt]
  (let [row-count (.Count (.Rows dt))]
    (if (> row-count 0)
      (do
	(let [col-names (map #(keyword (.ColumnName %)) (.Columns dt))]
	  (map #(zipmap col-names (.ItemArray %)) (.Rows dt)))
	 )
     ))
  )

(defn run-sql 
  "Runs the provided sql and loads a DataTable object with the results.  
  The function db.core/resultset-seq is called convert the data in the
  DataTable over to a sequence of maps.  Each map represents a row in 
  the DataTable."
  [sql-str]
  (if (not= (str (.State dbconn)) "Open") (.Open dbconn))
  (let [reader (.ExecuteReader (NpgsqlCommand. sql-str dbconn))
        data-table (DataTable.)]
    (.Load data-table reader)
    (.Close reader)
    (resultset-seq data-table)))

(defn ^{:private true}
  get-player-demog 
  "Gets the demographic information for the player"
 [last-name]
	(first (run-sql (str "select * from master where lastname ilike '" last-name "'"))))

(defn ^{:private true}
  get-scoring-stats 
  "Gets goals, assists, shots, etc for any player who has recorded at least
   one offensive stat in his career"
   [player-id]
   (run-sql (str "select * from scoring where playerid = '" player-id "'")))

(defn ^{:private true}
  get-goalie-stats 
  "Gets goal against, save percentage, etc for any player who played in the goal" 
  [player-id]
  (run-sql (str "select * from goalies where playerid = '" player-id "'")))

(defn get-player 	
  "Gets the players demographics, scoring stats and goalie stats returned
   in a map that has the following keys:
   :demog :scoring :goalie"
  [lastname]
    (let [demog (get-player-demog lastname)
	  playerid (:playerid demog)]
	{:demog demog
	 :scoring (get-scoring-stats playerid)
	 :goalie (get-goalie-stats playerid)}))

(defn -getPlayer 
  "The function that will be called from C#. It is a wrapper for the 
  get-player function"
  [lastname]
  (get-player lastname))
