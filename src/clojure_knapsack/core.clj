(ns clojure-knapsack.core
  (:require [clojure.string :as str]))

(defn read-lines
  "Read lines in filename into sequence"
  [filename]
  (str/split-lines (slurp filename)))

(defn make-max-weight [line]
  (Integer/parseInt line))

(defn parse-doll
  "Make a map from the name, weight, and value strings of a doll"
  [[name weight value]]
  (zipmap [:name :weight :value]
          [name (Integer/parseInt weight) (Integer/parseInt value)]))

(defn make-doll [line]
  (parse-doll (str/split line #"\s+")))

(defn parse-input [filename]
  (let [[weight-line & doll-lines] (read-lines filename)]
    {:max-weight (make-max-weight weight-line)
     :dolls (map make-doll doll-lines)}))

(defn build-knapsack-table [scenario]
  (repeat (+ 1 (:max-weight scenario)) 0))

;(print-result (build-knapsack-table (parse-input file)))
