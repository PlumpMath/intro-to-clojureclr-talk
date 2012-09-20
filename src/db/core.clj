; the first two assemblies are required for using the mono version of 
; the postgresql data provider
(assembly-load-from ".\\deps\\mono.security.dll")
(assembly-load-from ".\\deps\\npgsql.dll")
(System.Reflection.Assembly/LoadWithPartialName "System.Data")

(ns db.core
 (:import (Npgsql NpgsqlConnection NpgsqlCommand)
          (System.Data DataTable)))

(def ^{:private true}
   conn-str "Server=127.0.0.1;Port=5432;Database=hockeydb;User Id=postgres;Password=postgres;")

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
      )))

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
