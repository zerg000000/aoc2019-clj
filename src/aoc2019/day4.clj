(ns aoc2019.day4
  (:require [clojure.string :as string]))

(def input (range 153517 630395))

(defn double-number? [num]
  (-> num
      (set)
      (count)
      (< 6)))

(defn mono-inc? [num]
  (= num (sort num)))

(def xf (comp (map str)
              (map set)
              (filter mono-inc?)
              (filter double-number?)))

(time (count (into [] xf input)))
