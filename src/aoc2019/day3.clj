(ns aoc2019.day3
  (:require [clojure.string :as string]
            [clojure.set :as set]))

(def paths (->> (slurp "inputs/day3.txt")
                (string/split-lines)
                (map #(string/split % #","))))

(def path ["R8" "U5" "L5" "D3"])
(def path2 ["U7" "R6" "D4" "L4"])

(defn move-to [pos inst]
  (let [direction (first inst)
        move (Integer/parseInt (subs inst 1))]
    (case direction
      \R (update pos 0 + move)
      \L (update pos 0 - move)
      \U (update pos 1 + move)
      \D (update pos 1 - move))))

(defn expand-line [[x1 y1] [x2 y2]]
  (if (= x1 x2)
    (mapv (fn [y] [x1 y]) (apply range (sort [(inc y1) (inc y2)])))
    (mapv (fn [x] [x y1]) (apply range (sort [(inc x1) (inc x2)])))))

(defn path->pts [p]
  (loop [cur [0 0]
         path p
         pts [[0 0]]]
    (if (seq path)
      (let [next-cur (move-to cur (first path))]
        (recur next-cur (rest path) (into pts (expand-line cur next-cur))))
      pts)))

(path->pts path)
(path->pts path2)

(require '[clojure.set :as set])


(defn closest-cross [path path2]
  (->> (set/intersection (set (path->pts path))
                         (set (path->pts path2)))
       (sort-by (fn [[x y]] (+ (Math/abs x) (Math/abs y))))
       (second)))

(closest-cross path path2)

(closest-cross
 ["R75" "D30" "R83" "U83" "L12" "D49" "R71" "U7" "L72"]
 ["U62" "R66" "U55" "R34" "D71" "R55" "D58" "R83"])

(apply closest-cross paths)
