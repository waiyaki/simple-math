(ns simple-math.core)


(defn geek-print
  "Print output like those hacker terminals in movies."
  [s & {:keys        [final-newline delay-ms]
        :or {final-newline true delay-ms 20}}]
  (doseq [c s]
    (print c)
    (flush)
    (Thread/sleep delay-ms))
  (when final-newline
    (print "\n")))


(def preferences
  {:levels {:1 "Level 1 (Class 2 - 3)"
            :2 "Level 2 (Class 3 - 4)"
            :3 "Level 3 (Class 4 - 5)"}})


(def parsers
  {:int   #(Integer/parseInt %)
   :float #(Float/parseFloat %)})


(defn print-preferences [preferences pref & {:keys [geek] :or {geek true}}]
  (doseq [[idx desc] (pref preferences)]
    (if geek
      (geek-print (format "%s. %s" (name idx) desc))
      (print (format "%s. %s" idx desc)))))


(defn prompt
  ([] (prompt ""))
  ([q] (prompt q {}))
  ([q {:keys [parser shell-prompt print-prompt] :or {shell-prompt "> " print-prompt true}}]
   (if (fn? q)
     (q)
     (geek-print (format "%s%s" (if print-prompt shell-prompt "") q)))
   (when parser
     (read))))


(defn level-pref []
  (prompt "Please choose your class:" {:print-prompt false})
  (prompt #(print-preferences preferences :levels))
  (prompt "" {:parser (:int parsers)}))


(defn -main []
  (println "This is main!!")
  (let [level (level-pref)]
    (println "Level:" level)))
