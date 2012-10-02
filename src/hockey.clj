(System.Reflection.Assembly/LoadWithPartialName "System.Windows.Forms")
(System.Reflection.Assembly/LoadWithPartialName "System.Configuration")

(ns hockey
  (:import [System.Windows.Forms Button GroupBox CheckedListBox Form
	    TableLayoutPanel MessageBox PaintEventHandler Label TextBox
            DataGridView])
  (:import [System.Drawing Size Font FontStyle Point GraphicsUnit])
  (:import [System.ComponentModel BackgroundWorker DoWorkEventHandler
            RunWorkerCompletedEventHandler RunWorkerCompletedEventArgs])
  (:require [db.queries :as query])
  (:gen-class))

(def qry-results (atom {}))

(defn- convert-to-object-array [{:keys [year tmid pos gp g a pts pims plusminus ppg ppa shg
                                        sha gwg gtg sog]}]
  (into-array Object [year tmid pos gp g a pts pims plusminus ppg ppa shg
                                        sha gwg gtg sog]))

(defn- add-row-to-grid [grid data]
   (.Add (.Rows grid) (convert-to-object-array data)))

(defn- create-grid-and-columns []
  (let [scoring-grid (DataGridView.)]
    (set! (.ColumnCount scoring-grid) 16)
    (set! (.Name (nth (.Columns scoring-grid) 0)) "Season")
    (set! (.Name (nth (.Columns scoring-grid) 1)) "Team")
    (set! (.Name (nth (.Columns scoring-grid) 2)) "POS")
    (set! (.Name (nth (.Columns scoring-grid) 3)) "GP")
    (set! (.Name (nth (.Columns scoring-grid) 4)) "G")
    (set! (.Name (nth (.Columns scoring-grid) 5)) "A")
    (set! (.Name (nth (.Columns scoring-grid) 6)) "PTS")
    (set! (.Name (nth (.Columns scoring-grid) 7)) "PIMS")
    (set! (.Name (nth (.Columns scoring-grid) 8)) "+/-")
    (set! (.Name (nth (.Columns scoring-grid) 9)) "PPG")
    (set! (.Name (nth (.Columns scoring-grid) 10)) "PPA")
    (set! (.Name (nth (.Columns scoring-grid) 11)) "SHG")
    (set! (.Name (nth (.Columns scoring-grid) 12)) "SHA")
    (set! (.Name (nth (.Columns scoring-grid) 13)) "GWG")
    (set! (.Name (nth (.Columns scoring-grid) 14)) "GTG")
    (set! (.Name (nth (.Columns scoring-grid) 15)) "SOG")

    (doto scoring-grid 
      (.set_Name "Stats")
      (.set_Location (Point. 4 110))
      (.set_Size (Size. 975 675)))

    scoring-grid))

(defn- create-scoring-grid [form]
  (let [ scoring-grid (create-grid-and-columns)
         scoring (:scoring @qry-results)]
    
    (if (= (.Length (.Find (.Controls form) "Stats" true)) 1)
      (.Remove (.Controls form) (nth (.Find (.Controls form) "Stats" true) 0)))
            
    ;(doseq [rec scoring]
    (doseq [rec (:scoring @qry-results)]
      (add-row-to-grid scoring-grid rec))

    (.Add (.Controls form) scoring-grid)))

(defn -main []
  (let [form (Form.)
        dialog (Form.)
        dialog-lbl (Label.)
        player-name-lbl (Label.)
        search-lbl (Label.)
        search-txt (TextBox.)
        search-btn (Button.)
        group-box (GroupBox.)
        background-worker (BackgroundWorker.)
        title-str "MyClojureAdventure.com - Hockey Player Lookup"]

    (doto dialog
      (.set_Height 100)
      (.set_Text title-str))

    (doto dialog-lbl 
      (.set_Size (Size. 280 22))
      (.set_Font (Font. "Microsoft Sans Serif"
			12.0 FontStyle/Bold GraphicsUnit/Point 0)))

    (doto group-box 
      (.set_Text "Search")
      (.set_Location (Point. 5 5))
      (.set_Size (Size. 320 60)))

    (doto player-name-lbl
      (.set_Size (Size. 480 22))
      (.set_Location (Point. 5 85))
      (.set_Font (Font. "Microsoft Sans Serif"
			12.0 FontStyle/Bold GraphicsUnit/Point 0)))

    (doto search-lbl
      (.set_Text "Last Name: ")
      (.set_Location (Point. 12 27))
      (.set_Size (Size. 70 22)))

    (doto search-txt
      (.set_Width 120)
      (.set_Location (Point. 90 25)))

    (doto search-btn
      (.set_Text "Get Stats!")
      (.set_Location (Point. 220 25)))

    (doto (.Controls dialog)
      (.Add dialog-lbl))

    (doto (.Controls form)
      (.Add search-lbl)
      (.Add search-txt)
      (.Add search-btn)
      (.Add group-box))

    (.add_DoWork background-worker 
      (gen-delegate DoWorkEventHandler [sender e]
        (let [name (.Argument e)]
              (reset! qry-results (query/get-player name))))) 

    (.add_RunWorkerCompleted background-worker
      (gen-delegate RunWorkerCompletedEventHandler [sender e]
        (.Hide dialog)
        (let [demog (:demog @qry-results)]
          (.set_Text player-name-lbl 
                (str (:firstname demog) " " (:lastname demog) " (" (:pos demog) ")"))
          (.Add (.Controls form) player-name-lbl)
          (create-scoring-grid form))))

    (.add_Click search-btn
      (gen-delegate EventHandler [sender args]
        (.set_Text dialog-lbl (str "Searching for " (.Text search-txt) "..."))
        (.RunWorkerAsync background-worker (.Text search-txt))
        (.ShowDialog dialog)))

    (doto form
      (.set_Text title-str)
      (.set_Size (Size. 1024 850))
      .ShowDialog)
  (println title-str)))
