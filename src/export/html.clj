(ns export.html)

(defn create-table-row [tag data]
  (str "\t<tr>\n\t" (apply str (map #(str "\t\t<" tag ">" % "</" tag ">\n") data)) "</tr>\n"))

(defn create-shooting-stats [{:keys [year tmid pos gp g a pts pim 
                                     plusminus ppg ppa shg sha gwg gtg 
                                     sog]}]
    (create-table-row "td" [year tmid pos gp g a pts pim 
        plusminus ppg ppa shg sha gwg gtg 
        sog]))

(defn create-playoff-shooting-stats [{:keys [year tmid pos postgp postg posta
                                             postpts postpim postplusminus 
                                             postppg postppa postshg postsha 
                                             postgwg postgtg postsog]}]
    (create-table-row "td" [year tmid pos postgp postg posta
                                             postpts postpim postplusminus 
                                             postppg postppa postshg postsha 
                                             postgwg postgtg postsog]))

(defn shooting-stats-table-header []
 (create-table-row "th" ["Season" "Teamid" "Pos" "GP" "G" "A" "PTS" "PIMS" 
                         "+/-" "PPG" "PPA" "SHG" "SHA" "GWG" "GTG" "SOG"]))

(defn show-shooting-season-stats [stats]
  (str "<h3>Regular Season</h3>\n"
       "<table class=\"table\">\n"
	"<thead>\n"
         (shooting-stats-table-header)
       "<tbody>\n\t"
       (apply str (map create-shooting-stats stats))
       "\n</tbody></table>"))

(defn show-shooting-playoff-stats [stats]
  (str "<h3>Regular Season</h3>\n"
       "<table class=\"table\">\n"
	"<thead>\n"
         (shooting-stats-table-header)
       "<tbody>\n\t"
       (apply str (map create-playoff-shooting-stats stats))
       "\n</tbody></table>"))


(defn create-shooting-stats-html [stats]
    (str "<div class=\"scoring\">\n<h2>Scoring</h2>"
         (show-shooting-season-stats stats)
         "<br>"
	 (show-shooting-playoff-stats stats)
         "</div>"))

(defn create-goalie-stats [{:keys [year tmid gp min w l tol eng sho ga sa postgp postmin postw
																							postl postt posteng postsho postga postsa]}]
			[:tr
        [:td year]
			  [:td tmid]
				[:td gp]
				[:td min]
				[:td w]
				[:td l]
				[:td tol]
				[:td eng]
				[:td sho]
				[:td ga]
				[:td sa]])

(defn create-goalie-playoff-stats [{:keys [year tmid postgp postmin postw
																							postl postt posteng postsho postga postsa]}]
			[:tr
        [:td year]
			  [:td tmid]
				[:td postgp]
				[:td postmin]
				[:td postw]
				[:td postl]
				[:td postt]
				[:td posteng]
				[:td postsho]
				[:td postga]
				[:td postsa]])

(defn goalie-stats-table-header []
	[:thead
		[:th "Season"]
		[:th "Teamid"]
		[:th "GP"]
		[:th "Min"]
		[:th "W"]
		[:th "L"]
		[:th "TOL"]
		[:th "ENG"]
		[:th "SHO"]
		[:th "GA"]
		[:th "SA"]])

(defn show-goalie-regular-season-stats [goalies]
	[:h3 "Regular Season"]
  [:table.table
	  (goalie-stats-table-header)
		[:tbody (map create-goalie-stats goalies)]])

(defn show-goalie-playoff-stats [goalies]
	[:h3 "Post Season"]
  [:table.table
		(goalie-stats-table-header)
		[:tbody (map create-goalie-playoff-stats goalies)]])

(defn show-goalie-stats [goalies]
	[:div.goalies
	    (show-goalie-regular-season-stats goalies)
			[:br]
			(show-goalie-playoff-stats goalies)])

(defn show-player-demog [{:keys [firstname lastname pos]}]
	[:div.demog
	 [:div.page-header.tabs.clearfix.nm
	  [:h2 (str firstname " " lastname)
	  [:span.pos (str "(" pos ")") ]]]])

(defn show-stats [data]
  (let [demog (:demog data)]
		(str (show-player-demog demog)
	  (if (= (:pos demog) "G")
      (str (show-goalie-stats (:goalie data))
		      (show-shooting-stats (:scoring data)))
	    (show-shooting-stats (:scoring data))))))
	
