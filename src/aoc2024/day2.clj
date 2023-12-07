(ns aoc2024.day2
  (:require [instaparse.core :as insta]
            [clojure.string :as str]))

(def parser
  (insta/parser
   "
    S: Game ColorList {<'; '> ColorList}
    Num: #'\\d+'
    Game: <'Game '> Num <': '>
    Color: 'red' | 'green' | 'blue'
    ColorCount: Num <' '> Color
    ColorList: ColorCount (<', '> ColorCount)*
    "))

(defn is-valid [cubes]
  (and
   (>= 12 (get cubes "red"))
   (>= 13 (get cubes "green"))
   (>= 14 (get cubes "blue"))))

(defn max_sum [cubes]
  (*
   (get cubes "red")
   (get cubes "green")
   (get cubes "blue")))

(defn traverse [counts [tag & elems]]
  (case tag
    :Game (assoc counts :id (-> elems (nth 0) (nth 1) parse-long))
    :ColorCount (let [[[_:Num num] [_:Color color]] elems]
                  (assoc counts color (max (get counts color 0) (parse-long num))))
    (reduce traverse counts elems)))

(defn trace [t] (doto t println))

(defn ex1 [in]
  (->> in
       str/split-lines
       (map parser)
       (map #(traverse {} %))
       (filter is-valid)
       (map #(get % :id))
       (reduce +)))

(defn ex2 [in]
  (->> in
       str/split-lines
       (map parser)
       (map #(traverse {} %))
       (map max_sum)
       trace
       (reduce +)))