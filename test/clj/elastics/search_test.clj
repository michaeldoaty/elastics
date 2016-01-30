(ns elastics.search-test
  (:require [clojure.test :refer :all]
            [elastics.search :as search]))

(def body {:greet "hello"})

(deftest query-test
  (testing "with no arguments"
    (is (= (search/query)
           {:method :get
            :url    "_search"
            :body   nil})))

  (testing "with body"
    (is (= (search/query body)
           {:method :get
            :url    "_search"
            :body   body})))

  (testing "body and index"
    (is (= (search/query body "twitter")
           {:method :get
            :url    "twitter/_search"
            :body   body})))

  (testing "body, index, type"
    (is (= (search/query body "twitter" "tweet")
           {:method :get
            :url    "twitter/tweet/_search"
            :body   body}))))



(deftest template-test
  (is (= (search/template body)
         {:method :get
          :url "template"
          :body body})))



(deftest shards-test
  (testing "index"
    (is (= (search/shards "twitter")
           {:method :get
            :url    "twitter/_search_shards"})))

  (testing "index and type"
    (is (= (search/shards "twitter" "tweet")
           {:method :get
            :url    "twitter/tweet/_search_shards"}))))



(deftest suggest-test
  (is (= (search/suggest body)
         {:method :get
          :url "_suggest"
          :body body})))



(deftest count-test
  (testing "without body"
    (is (= (search/count "twitter" "tweet")
           {:method :get
            :body nil
            :url "twitter/tweet/_count"})))

  (testing "with body"
    (is (= (search/count body "twitter" "tweet")
           {:method :get
            :body body
            :url "twitter/tweet/_count"}))))



(deftest validate-test
  (testing "without arguments"
    (is (= (search/validate)
           {:method :get
            :body nil
            :url "_validate/query"})))

  (testing "with body"
    (is (= (search/validate body)
           {:method :get
            :body body
            :url "_validate/query"})))

  (testing "body and index"
    (is (= (search/validate body "twitter")
           {:method :get
            :body body
            :url "twitter/_validate/query"})))

  (testing "body, index, and type"
    (is (= (search/validate body "twitter" "tweet")
           {:method :get
            :body body
            :url "twitter/tweet/_validate/query"}))))



(deftest explain-test
  (is (= (search/explain body "twitter" "tweet" 1)
         {:method :get
          :body body
          :url "twitter/tweet/1/_explain"})))
