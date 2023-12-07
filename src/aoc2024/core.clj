(ns aoc2024.core
  (:gen-class)
  (:require [aoc2024.day1])
  (:require [aoc2024.day2]))

(def exs
  [[aoc2024.day1/ex1 aoc2024.day1/ex2]
   [aoc2024.day2/ex1 aoc2024.day2/ex2]])


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let
   [day (- (parse-long (nth args 0)) 1)
    ex (- (parse-long (nth args 1)) 1)
    file (slurp (nth args 2))]
    (print ((nth (nth exs day) ex) file))))
