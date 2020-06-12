(ns augmenter.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
;;////////////////////////////////
(defn inp []
  (println "Input map")
  (let [input (read)]
    input))

(def input (inp))                                           ;;input
(println input)
;;////////////////////////////////                          ;;turns maps into vector
(def vectorized (into [] (map vec (partition 2 (reduce into [] input)))))
;;////////////////////////////////
;;  (get (last (nth vectorized 3)) :input)
;;  (vector (first (nth vectorized 2)))
;;  (some #(= (first (nth vectorized 2)) %) (get (last (nth vectorized 3)) :input))
;;///////////////////////////////                         ;;bubble steps
(defn bubble [one two]
  (let [lastOne (last one)]
    (if (some #(= % (first two)) (get (last lastOne) :input))
      (conj (pop one) two lastOne)
      (conj one two))))
;;///////////////////////////////
(defn bubble-sort [vectorized]
  (let [output (reduce bubble [(first vectorized)] (rest vectorized))]
    (if (= output vectorized)
      vectorized                                            ;;WTF?????
      (recur output))))
(bubble-sort vectorized)
(conj (pop [1 2 3]) 10 20)



;;/////
(defn bubble2 [ys x]
  (if-let [y (peek ys)]
    (if (> y x)
      (conj (pop ys) x y)
      (conj ys x))
    [x]))

(defn bubble-sort2 [xs]
  (let [ys (reduce bubble2 [] xs)]
    (if (= xs ys)
      xs
      (recur ys))))
(bubble-sort2 [4 5 1 2 6])
