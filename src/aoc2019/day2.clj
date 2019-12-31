(ns aoc2019.day2
  (:require [clojure.string :as string]))

(def mem (-> (slurp "inputs/day2.txt")
             (string/split #",")
             (->> (mapv #(Integer/parseInt %)))))

(defn read-op [mem cur]
  (condp = (get mem cur)
    1 [(->> mem (drop cur) (take 4)) (+ cur 4)]
    2 [(->> mem (drop cur) (take 4)) (+ cur 4)]
    99 [(->> mem (drop cur) (take 1)) (+ cur 1)]
    nil))

(read-op mem 0)
(read-op mem 4)
(read-op mem 8)

(defn compute [mem]
  (loop [cur 0
         m mem]
    (let [[[op idx1 idx2 idx3] next-cur] (read-op m cur)]
      (condp = op
        1 (recur next-cur (assoc m idx3 (+ (get m idx1) (get m idx2))))
        2 (recur next-cur (assoc m idx3 (* (get m idx1) (get m idx2))))
        99 m))))

(-> mem
    (assoc 1 12 2 2)
    (compute))
