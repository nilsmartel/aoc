(ns day2.core
  (:gen-class)
  (:require [clojure.string :as str])
  (:require [clojure.core.match :refer [match]]))

(defn parseLine [line] (let [[dir value] (str/split line #" ")] {:dir dir
                                                                 :value (read-string value)}))

(defn parse [text] (map parseLine (str/split text #"\n")))

(defn position [instructions] (reduce (fn [[h d] {:keys dir value}]
                                        (match [dir]  ["forward"] [(+ h value) d]
                                               ["down"] [h (+ d value)]
                                               ["up"] [h (- d value)])) [0 0] instructions))

(defn -main
  [input & _args]
  (let [data (-> input slurp parse)]
    (print (data))))
