(ns day1.core
  (:gen-class)
  (:require [clojure.string :as string]))



(defn parse [text] (let [lines (string/split text #"\n")] (map read-string lines)))

(defn sum [l] (reduce + 0 l))

(defn followIncrease
  "takes a seq of numbers and returns the amount of times, a number is greater than it's predecessor"
  [numbers] (sum (map (fn [fst snd] (if (> snd fst) 1 0)) numbers (drop 1 numbers))))

(defn aggr3 [l]
  (map +
       l (drop 1 l) (drop 2 l)))

(defn -main
  "I don't do a whole lot ... yet."
  [input & _args]
  (let [numbers (parse (slurp input))]
    (println "task 1: " (followIncrease numbers))
    (println "task 2: " (-> numbers aggr3 followIncrease))))
