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
  (is (= 1030 ((build-knapsack-table
                      (parse-input "resources/atomic-ex.txt")) [22 400]))))
