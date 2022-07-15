(ns day1.core
  (:gen-class))



(defn parse [text] (let [lines (clojure.string/split text #"\n")] (map read-string lines)))

(defn sum [l] (reduce + 0 l))

(defn -main
  "I don't do a whole lot ... yet."
  [input & _args]
  (let [numbers (parse (slurp input))]
    (print (sum (map (fn [fst snd] (if (> snd fst) 1 0)) numbers (drop 1 numbers))))))
