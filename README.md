# clojure-knapsack

Solution for Atomic's challenge

## Usage

    ~/git/clojure-knapsack » lein test
    
    lein test clojure-knapsack.core-test
    
    Ran 8 tests containing 8 assertions.
    0 failures, 0 errors.

    ~/git/clojure-knapsack » lein repl
    nREPL server started on port 41698
    user=> (use 'clojure-knapsack.core)
    nil
    user=> (print-result (solve "resources/atomic-ex.txt"))
    packed dolls:
    name weight value
    sally 4 50
    eddie 7 20
    grumpy 22 80
    dusty 43 75
    grumpkin 42 70
    marc 11 70
    randal 27 60
    puppy 15 60
    dorothy 50 160
    candice 153 200
    anthony 13 35
    luke 9 150
    nil
    user=> 

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
