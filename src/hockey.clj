(System.Reflection.Assembly/LoadWithPartialName "System.Windows.Forms")

(ns hockey
  (:import [System.Windows.Forms Button GroupBox CheckedListBox Form
	    TableLayoutPanel MessageBox PaintEventHandler Label TextBox
            DataGridView])
  (:import [System.Drawing Size Font FontStyle Point GraphicsUnit])
  (:import [System.ComponentModel BackgroundWorker DoWorkEventHandler
            RunWorkerCompletedEventHandler RunWorkerCompletedEventArgs])
  (:require [db.queries :as query])
  (:gen-class))

(defn -main []
  (let [form (Form.)
        dialog (Form.)
        dialog-lbl (Label.)
        player-name-lbl (Label.)
        search-lbl (Label.)
        search-txt (TextBox.)
        search-btn (Button.)
        group-box (GroupBox.)
        results-grid (DataGridView.)
        background-worker (BackgroundWorker.)
        qry-results (atom {})
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
              (swap! qry-results merge (query/get-player name))
          (.set_Result e (:firstname (:demog @qry-results))))))

    (.add_RunWorkerCompleted background-worker
      (gen-delegate RunWorkerCompletedEventHandler [sender e]
        (.Hide dialog)
        (MessageBox/Show (.Result e))))

    (.add_Click search-btn
      (gen-delegate EventHandler [sender args]
        (.set_Text dialog-lbl (str "Searching for " (.Text search-txt) "..."))
        (.RunWorkerAsync background-worker (.Text search-txt))
        (.ShowDialog dialog)))

    (doto form
      (.set_Text title-str)
      (.set_Size (Size. 800 600))
      .ShowDialog)
  (println title-str)))
