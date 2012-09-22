(System.Reflection.Assembly/LoadWithPartialName "System.Windows.Forms")

(ns core
  (:import [System.Windows.Forms Button GroupBox CheckedListBox Form
	    TableLayoutPanel MessageBox PaintEventHandler Label TextBox])
  (:import [System.Drawing Size Font FontStyle Point])
  (:gen-class))

(defn -main []
  (let [form (Form.)
        search-lbl (Label.)
        search-txt (TextBox.)
        search-btn (Button.)
        group-box (GroupBox.)
        title-str "MyClojureAdventure.com - Hockey Player Lookup"]

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

    (doto (.Controls form)
      (.Add search-lbl)
      (.Add search-txt)
      (.Add search-btn)
      (.Add group-box))

    (doto form
      (.set_Text title-str)
      (.set_Size (Size. 800 600))
      .ShowDialog)
  (println title-str)))
