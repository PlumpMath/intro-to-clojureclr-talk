; the first two assemblies are required for using the mono version of 
; the postgresql data provider
(assembly-load-from ".\\deps\\mono.security.dll")
(assembly-load-from ".\\deps\\npgsql.dll")
(System.Reflection.Assembly/LoadWithPartialName "System.Data")

(ns db.queries
 (:use db.core)
 (:import (Npgsql NpgsqlConnection NpgsqlCommand)
          (System.Data DataTable))
 (:gen-class
  :methods [ #^{:static true} [getPlayer [System.String] System.String]]))
  
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
