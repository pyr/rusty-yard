(ns rusty.yard-test
  (:require [clojure.test :refer [deftest testing is]]
            [rusty.yard   :refer [codename]]))

(def fake-corpus
  [:red :rusty :yard])

(def fake-suffixes
  [:balloon :animal])

(def possibilities
  #{ ;; Triple variant
    [:red :rusty :balloon]
    [:red :rusty :animal]
    [:red :yard :balloon]
    [:red :yard :animal]
    [:rusty :red :balloon]
    [:rusty :red :animal]
    [:rusty :yard :balloon]
    [:rusty :yard :animal]
    [:yard :red :balloon]
    [:yard :red :animal]
    [:yard :rusty :balloon]
    [:yard :rusty :animal]
    [:red :red :balloon]
    [:red :red :animal]
    [:rusty :rusty :balloon]
    [:rusty :rusty :animal]
    [:yard :yard :balloon]
    [:yard :yard :animal]

    ;; Suffix first variant
    [:balloon :red]
    [:balloon :rusty]
    [:balloon :yard]
    [:animal :red]
    [:animal :rusty]
    [:animal :yard]
    ;; Suffix last variant
    [:red :balloon]
    [:red :animal]
    [:rusty :balloon]
    [:rusty :animal]
    [:yard :balloon]
    [:yard :animal]})

(deftest basic-behavior
  (testing "Returns a string by default"
    (is (string? (codename)))
    (is (string? (codename :separator "-")))
    (is (string? (codename {:separator "-"}))))

  (testing "Returns a vector when instructed to"
    (is (vector? (codename :join? false)))
    (is (vector? (codename {:join? false})))
    ;; join? takes precedence over separator
    (is (vector? (codename :join? false :separator "-")))
    (is (vector? (codename {:join? false :separator "-"}))))

  (testing "Generation can be determined"
    (is (every? #(contains? possibilities %)
                (take 100
                      (repeatedly #(codename {:join? false
                                              :corpus fake-corpus
                                              :suffixes fake-suffixes})))))))
