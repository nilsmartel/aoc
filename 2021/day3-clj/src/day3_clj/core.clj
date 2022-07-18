(ns day3-clj.core
  (:use [clojure.core :only [bit-shift-left, bit-and]])
  (:require [clojure.string :as str])
  (:gen-class))

(def filename "input")
(def bitlength 12)

(defn zeros [n] (repeat n 0))

(defn modRow
  ([row] (modRow row (zeros bitlength)))
  ([row v] (map (fn [index value]
                  (let [
                       bitfieldindex (dec (- bitlength index))
                       bit (bit-and row (bit-shift-left bitfieldindex 1))
                       zero (= 0 bit)
                       increment (if zero 0 1)
                       ]
                      (+ value increment))
                  ) (range bitlength) v)
   ))

(defn parseBinary
  ([s] (parseBinary (seq s) 0))
   ([s v] (cond
           (empty? s) v
           (= \1 (first s)) (parseBinary (rest s) (inc (bit-shift-left v 1)))
            :else   (parseBinary (rest s) (bit-shift-left v 1)))))

(defn parseText [s] (map parseBinary (str/split s #"\n")))

(defn -main
  "I don't do a whole lot ... yet."
  [& _args]
  (let
       [numbers (->  filename slurp parseText)]
       (println numbers)))


; entry point for babashka
;(-main)
;(zeros 4)
(dotimes [n 12]
  (println (modRow (bit-shift-left 1 n))))
