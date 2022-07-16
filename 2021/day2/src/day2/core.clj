(ns day2.core
  (:gen-class)
  (:require [clojure.string :as str])
  (:require [clojure.core.match :refer [match]]))

(defn parseLine [line] (let [[dir value] (str/split line #" ")] {:dir dir
                                                                 :value (read-string value)}))

(defn parse [text] (map parseLine (str/split text #"\n")))

(defn position [instructions] (reduce (fn [[h d] {dir :dir value :value}]
                                        (match [dir]  ["forward"] [(+ h value) d]
                                               ["down"] [h (+ d value)]
                                               ["up"] [h (- d value)])) [0 0] instructions))

(defn position2 [instructions] (reduce (fn [[h d aim] {dir :dir value :value}]
                                        (match [dir]
                                               ["forward"] [(+ h value) (+ d (* aim value)) aim]
                                               ["down"] [h d (+ aim value)]
                                               ["up"] [h d (- aim value)]))
                                       [0 0 0] instructions))

(defn -main
  [input & _args]
  (let [data (-> input slurp parse)
        [h d] (position data)
        [h2 d2 _aim] (position2 data)
        ]
    (println "horizontal" h)
    (println "depth" d)
    (println "multiplied" (* h d))
    (println "-----")
    (println "multiplied" (* h2 d2))
    ))
