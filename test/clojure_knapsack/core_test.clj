(ns clojure-knapsack.core-test
  (:use clojure.test
        clojure-knapsack.core))

(defmacro file [] "test/small.txt")
(defmacro doll-map [] {:value 150, :name "luke", :weight 9})
(defmacro scenario []
  {:max-weight 20 :dolls [{:value 150, :weight 9, :name "luke"}
                          {:value 35, :weight 13, :name "anthony"}]})

(deftest read-lines-test
  (testing "Check read-lines"
    (is (= '("20" "luke        9   150" "anthony    13    35")
           (read-lines (file))))))

(deftest make-max-weight-test
  (testing "Check make-max-weight"
    (is (= 400 (make-max-weight "400")))))

(deftest parse-doll-test
  (testing "Check parse-doll"
    (is (= (doll-map) (parse-doll ["luke" "9" "150"])))))

(deftest make-doll-test
  (testing "Check make-doll"
    (is (= (doll-map) (make-doll "luke  9 150")))))

(deftest parse-input-test
  (testing "Check parse-file"
    (is (= (scenario) (parse-input (file))))))

(deftest init-table-test
  (testing "Check init-table"
    (is (= {[0 1] 0 [0 0] 0 [0 2] 0} (init-table 2)))))

(deftest build-knapsack-table-test
  (is (= 1030 ((:table (build-knapsack-table
                      (parse-input "resources/atomic-ex.txt"))) [22 400]))))

(deftest solve-example-test
  (is (= [{:value 50, :weight 4, :name "sally"}
          {:value 20, :weight 7, :name "eddie"}
          {:value 80, :weight 22, :name "grumpy"}
          {:value 75, :weight 43, :name "dusty"}
          {:value 70, :weight 42, :name "grumpkin"}
          {:value 70, :weight 11, :name "marc"}
          {:value 60, :weight 27, :name "randal"}
          {:value 60, :weight 15, :name "puppy"}
          {:value 160, :weight 50, :name "dorothy"}
          {:value 200, :weight 153, :name "candice"}
          {:value 35, :weight 13, :name "anthony"}
          {:value 150, :weight 9, :name "luke"}]
         (solve "resources/atomic-ex.txt"))))

(deftest solve-fsu-dataset
  (is (= (map #(first %) (reverse (filter (fn [itm] (= (second itm) 1))
                 (map-indexed (fn [idx itm] [idx itm])
                              [1 0 1 0 1 0 1 1 1 0 0 0 0 1 1]))))
         (map #(Integer/parseInt (:name %)) (solve "resources/15-ex.txt")))))

