(ns aoc2019.day1
  (:require [clojure.string :as string]))

(def inputs (->> (slurp "inputs/day1.txt")
                 (string/split-lines)
                 (map #(Integer/parseInt %))))

(defn required-fuel [num]
   (- (Math/floorDiv num 3) 2))

(comment
  (required-fuel 12)
  (required-fuel 14)
  (required-fuel 1969)
  (required-fuel 100756))

(reduce (fn [acc n]
          (+ acc (required-fuel n)))
        0 inputs)


