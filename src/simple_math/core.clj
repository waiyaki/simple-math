(ns simple-math.core)


(defn geek-print
  "Print output like those hacker terminals in movies."
  [s & {:keys [final-newline delay-ms]
        :or   {final-newline true delay-ms 20}}]
  (doseq [c s]
    (print c)
    (flush)
    (Thread/sleep delay-ms))
  (when final-newline
    (print "\n")))


(def preferences
  {:levels {1 "Level 1 (Class 2 - 3)"
            2 "Level 2 (Class 3 - 4)"
            3 "Level 3 (Class 4 - 5)"}})


(def parsers
  {:int   #(Integer/parseInt %)})


(defn print-preferences [preferences pref & {:keys [geek] :or {geek true}}]
  (doseq [[idx desc] (pref preferences)]
    (if geek
      (geek-print (format "%s. %s" idx desc))
      (print (format "%s. %s" idx desc)))))


(defn prompt
  "Prompt the user for some input. Runs the input through a parser if one is provided."
  ([] (prompt ""))
  ([q] (prompt q {}))
  ([q {:keys [parser shell-prompt print-prompt]
       :or   {parser identity shell-prompt "> " print-prompt true}}]
   (if (fn? q)
     (q)
     (geek-print (format "%s%s" (if print-prompt shell-prompt "") q)))
   (parser (read-line))))


(defn validate-preference
  "Ensure the selected preference is within allowable range."
  [preferences preference]
  (let [max-level (apply max (keys preferences))
        min-level (apply min (keys preferences))]
    (if (or (> preference max-level)
          (< preference min-level))
      (throw
        (ex-info
          "Invalid level."
          {:message (format "The selected level has to be between %s and %s"
                      min-level
                      max-level)}))
      preference)))


(defn attempt
  "Try a function a couple of times, give up if retries run out before a valid
  input is obtained."
  ([f] (attempt f 3))
  ([f retries]
   (loop [n retries]
     (when (> n 0)
       (let [result (try
                      (f)
                      (catch Exception e
                        (geek-print (format "%s %s"
                                      (ex-message e)
                                      (:message (ex-data e))))
                        (when (<= (dec n) 0)
                          (geek-print (format "Giving up after %s tries." retries)))))]
         (if result
           result
           (recur (dec n))))))))


(defn level-pref
  "Prompt for and obtain a user's preferred level."
  []
  (geek-print "Please choose your class:")
  (print-preferences preferences :levels)
  (attempt #(validate-preference (:levels preferences)
              (prompt "" {:parser (:int parsers)}))))


(defn -main []
  (println "This is main!!")
  (let [level (level-pref)]
    (println "Level:" level)))
