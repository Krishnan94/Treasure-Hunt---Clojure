(require '[clojure.string :as str])
(def treasuremap "")
(def path false)
(def row 0)
(def column 0)
(defn check [i j]
(if (< i 0) 
  false
  (if (< j 0) 
        false
        (if(>= i row) 
            false
            (if(>= j column)
              false               
              (if(= (str(aget treasuremap i j)) "#")
                false
                (if(= (str(aget treasuremap i j)) "!") 
                  false
                  )))))))
(defn correct [k l]
(if(= (str(aget treasuremap k l)) "@") 
  (do
  (def path true)
  true
  )
  false
))

(defn bfs [i j]
(def result false)
(def checked (check i j))
(if (= checked false)
  false  
  (if(= (correct i j) true)
    true
    (do (aset treasuremap i j "!") 
    (if(= (bfs (- i 1) j) false)
        (do
        (if(= (bfs (+ i 1) j) false)        
          (do
          (if(= (bfs i (- j 1)) false)          
              (do
              (if(= (bfs i (+ j 1)) false)
                    false
                    (do(aset treasuremap i j "+")            
                    true)
              ))
              (do(aset treasuremap i j "+")            
              true)
              )
             )
          (do(aset treasuremap i j "+")
              true)   
          )          
        )
        (do(aset treasuremap i j "+")
              true)
        )
              
        )
      )
    )
) 
(defn read-file-lines []
  (def string1 (slurp "map.txt"))  
  (def treasuremap ( to-array-2d (str/split string1 #"\n")))
  (def row (alength treasuremap ))
  (def column (alength (aget treasuremap 0)))
  (def invalid 1)
  (doseq [x (range row)]
    (if(not=(alength (aget treasuremap x)) column)
      (def invalid 0)
    )
  )
  ;; (doseq [x (range row)
  ;;       y (range column)]

  (if(= invalid 1)
  (do
  (println "This is my challenge:")
  (println)
  (def k 0) 
  (doseq [x (range row)
        y (range column)]
        (if (= k column)
          (do(def k 0)
            (println)))
          (do(print (aget treasuremap x y))
            (def k (+ k 1)))
  )
  (bfs 0 0)
  (println)
  (println)
  (if(= path true)
  (println "Woo hoo, I found the treasure :-)")
  (println "Uh oh, I could not find the treasure :-(")
  )
  (println)  
  (def k 0)  
  (doseq [x (range row)
        y (range column)]
        (if (= k column)
          (do(def k 0)
            (println)))
          (do(print (aget treasuremap x y))
            (def k (+ k 1)))
  )  
  )
  (println "Invalid Map") 
)
)
(read-file-lines)