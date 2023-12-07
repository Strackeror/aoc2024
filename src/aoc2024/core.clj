(ns aoc2024.core
  (:gen-class)
  (:require [clojure.string :as str]))

(def digit-strings
  [[0 "0" "zero"]
   [1 "1" "one"]
   [2 "2" "two"]
   [3 "3" "three"]
   [4 "4" "four"]
   [5 "5" "five"]
   [6 "6" "six"]
   [7 "7" "seven"]
   [8 "8" "eight"]
   [9 "9" "nine"]])

(defn find-first-digit [full-str]
  (->>
   (for [[n & rest] digit-strings]
     (for [num-str rest] [n num-str]))
   (apply concat)
   (map (fn [[n num-str]] [n (str/index-of full-str num-str)]))
   (filter #(some? (nth % 1)))
   (sort-by #(nth % 1))
   (first)
   (first)))

(defn find-last-digit [full-str]
  (->>
   (for [[n & rest] digit-strings]
     (for [num-str rest] [n num-str]))
   (apply concat)
   (map (fn [[n num-str]] [n (str/last-index-of full-str num-str)]))
   (filter #(some? (nth % 1)))
   (sort-by #(nth % 1))
   (reverse)
   (first)
   (first)))

(defn get-num2 [str]
  (+ (find-last-digit str) (* 10 (find-first-digit str))))

(defn day1-2 [str]
  (->> str
       str/split-lines
       (map get-num2)
       (reduce +)))


(defn is-digit [c] (Character/isDigit c))

(defn get-num
  [num-str]
  (let [digits (filter is-digit num-str)
        finaldigits (str (first digits) (last digits))]
    (parse-long (apply str finaldigits))))

(defn day1 [content] (reduce + (map get-num (str/split-lines content))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (print (day1-2 (slurp (nth args 0)))))
