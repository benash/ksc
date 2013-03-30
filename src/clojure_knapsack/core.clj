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

(defn init-table [max-weight]
  (zipmap (for [i (range (inc max-weight))] [0 i])
          (repeat (inc max-weight) 0)))

; Given the current table, fill in the cell at [i, j] and return the new map.
; If the doll weighs more than we're considering, use the value from the last
; doll. Otherwise, use either the val of last doll or the value from using the
; current doll along with the best value we can get from the remaining weight.
(defn fill-cell [weight-of value-of table [i j]]
  (let
    [last-doll-value (table [(dec i) j])]
    (assoc table [i j]
           (if (< j (weight-of i))
             last-doll-value
             (max last-doll-value
                  (+ (value-of i)
                     (table [(dec i) (- j (weight-of i))])))))))

(defn build-knapsack-table [scenario]
  (let [max-weight (:max-weight scenario)
        num-dolls (count (:dolls scenario))
        init (init-table max-weight)
        w (fn [i] (:weight (nth (:dolls scenario) (dec i))))
        v (fn [i] (:value (nth (:dolls scenario) (dec i))))
        partial-fill-cell (partial fill-cell w v)
        table (reduce partial-fill-cell init
                      (for [i (range 1 (inc num-dolls))
                            j (range (inc max-weight))] [i j]))]
    (assoc scenario :table table)))

(defn check-doll [scenario i]
  (let [capacity (:capacity scenario)
        table (:table scenario)
        solution (:solution scenario)
        doll (nth (:dolls scenario) (dec i))
        weight (:weight doll)]
    (do (println i capacity)
    (if (> (table [i capacity])
           (table [(dec i) capacity]))
      (merge scenario {:capacity (- capacity weight)
                       :solution (conj solution doll)})
      scenario)))
  )

(defn find-dolls [scenario]
  (let [num-dolls (count (:dolls scenario))
        max-weight (:max-weight scenario)
        value-up-to (fn [i] ((:table scenario) [i max-weight]))]
    (do (println ((:table scenario) [5 35]) ((:table scenario) [4 35]) )
        (:solution (reduce check-doll (merge scenario {:capacity max-weight
                                            :solution []})
                (range num-dolls 0 -1))))
))
        ;(print-result (find-dolls (build-knapsack-table (parse-input file))))

