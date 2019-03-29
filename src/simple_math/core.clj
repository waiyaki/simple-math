(ns simple-math.core
  (:require [clojure.string :as str]))


(def preferences
  {:levels {1 "Level 1 (Class 2 - 3)"
            2 "Level 2 (Class 3 - 4)"
            3 "Level 3 (Class 4 - 5)"}
   :ops    {1 "Addition"
            2 "Subtraction"
            3 "Multiplication"
            4 "Division"}})


(def option->op {1 +
                 2 -
                 3 *
                 4 /})


(def parsers
  {:int   #(Integer/parseInt %)})


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


(defn limits
  "Return the lower and upper limits of the numbers within which to generate
  math operations with, based on the user's limit"
  [level]
  ({1 [1 5]
    2 [1 12]
    3 [1 20]} level))


(defn random-int [min max]
  (int (Math/floor (+ min (* max (Math/random))))))


(defn gen-random-table
  "Generate rows of pairs of limited random numbers to use as questions for this level."
  [level]
  (let [[lower upper] (limits level)]
    (for [_ (range (if (< upper 10) 10 (max 15 upper)))]
      [(random-int lower upper) (random-int lower upper)])))


(defn attempt
  "Try a function a couple of times, give up if retries run out before a valid
  input is obtained."
  ([f] (attempt f {}))
  ([f {:keys [retries fail-silently]
       :or   {retries 3 fail-silently false} :as opts}]
   (loop [n retries]
     (when (> n 0)
       (let [result
             (try
               (f)
               (catch Exception e
                 (let [message (format "%s %s" (ex-message e)
                                 (if (<= (dec n) 0) "" (:message (ex-data e))))]
                   (geek-print message)
                   (when (and (not fail-silently) (<= (dec n) 0))
                     (throw
                       (ex-info message
                         {:message (format "Giving up after %s tries." retries)}))))))]
         (if result
           result
           (recur (dec n))))))))


(defn prompt
  "Prompt the user for some input. Runs the input through a parser if one is provided."
  ([] (prompt ""))
  ([q] (prompt q {}))
  ([q {:keys [parser shell-prompt print-prompt accept-blank]
       :or   {parser identity
              shell-prompt "> "
              print-prompt true
              accept-blank false}}]
   (let [prompt* (fn []
                   (if (fn? q)
                     (q)
                     (geek-print (format "%s%s" (if print-prompt shell-prompt "") q)))
                   (read-line))
         input (prompt*)]
     (loop [input input]
       (if (and (str/blank? input) (not accept-blank))
         (recur (prompt*))
         (parser input))))))


(defn validate-preference
  "Ensure the selected preference is within allowable range."
  [preferences preference]
  (let [max-level (apply max (keys preferences))
        min-level (apply min (keys preferences))]
    (if (or (> preference max-level)
          (< preference min-level))
      (throw
        (ex-info
          "Invalid option."
          {:message (format "The selection has to be between %s and %s"
                      min-level
                      max-level)}))
      preference)))


(defn print-preferences [preferences pref & {:keys [geek] :or {geek true}}]
  (doseq [[idx desc] (pref preferences)]
    (if geek
      (geek-print (format "%s. %s" idx desc))
      (print (format "%s. %s" idx desc)))))


(defn pref
  "Return a function which when invoked will prompt for and obtain a user's preference"
  [prompt-str preference & {:keys [parser]}]
  (fn []
    (geek-print prompt-str)
    (print-preferences preferences preference)
    (attempt #(validate-preference (preference preferences)
                (prompt "" {:parser (parser parsers)})))))


(def level-pref (pref "Please choose your class:"
                  :levels
                  :parser :int))


(def op-pref (pref "Please choose the math you want to do:"
                :ops
                :parser :int))


(defn -main []
  (println "This is main!!")
  (let [level     (level-pref)
        op-option (op-pref)
        op        (option->op op-option)]
    (println "Level:" level)
    (println "Op option" op-option)
    (println "Op: " op)))
