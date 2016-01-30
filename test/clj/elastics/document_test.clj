(ns elastics.document-test
  (:require [clojure.test :refer :all]
            [elastics.document :as doc]))

(def body {:greet "hello"})

(deftest index-test
  (testing "without index"
    (is (= (doc/index body "twitter" "tweet")
           {:method :post
            :body   body
            :url    "twitter/tweet"})))

  (testing "with index"
    (is (= (doc/index body "twitter" "tweet" 1)
           {:method :put
            :body   body
            :url    "twitter/tweet/1"}))))



(deftest get-test
  (is (= (doc/get "twitter" "tweet" 1)
         {:method :get
          :url    "twitter/tweet/1"})))



(deftest delete-test
  (is (= (doc/delete "twitter" "tweet" 1)
         {:method :delete
          :url "twitter/tweet/1"})))



(deftest update-with-script-test
  (is (= (doc/update-with-script body "twitter" "tweet" 1)
         {:method :post
          :url "twitter/tweet/1/_update"
          :body body})))



(deftest mget-test
  (testing "body"
    (is (= (doc/mget body)
           {:method :get
            :url "_mget"
            :body body})))

  (testing "body and index"
    (is (= (doc/mget body "twitter")
           {:method :get
            :url "twitter/_mget"
            :body body})))

  (testing "body, index, type"
    (is (= (doc/mget body "twitter" "tweet")
           {:method :get
            :url "twitter/tweet/_mget"
            :body body}))))



(deftest term-vectors-test
  (is (= (doc/term-vectors "twitter" "tweet" 1)
         {:method :get
          :url "twitter/tweet/1/_termvectors"})))



(deftest mterm-vectors-test
  (testing "body"
    (is (= (doc/mterm-vectors body)
           {:method :get
            :url "_mtermvectors"
            :body body})))

  (testing "body and index"
    (is (= (doc/mterm-vectors body "twitter")
           {:method :get
            :url "twitter/_mtermvectors"
            :body body})))

  (testing "body, index, type"
    (is (= (doc/mterm-vectors body "twitter" "tweet")
           {:method :get
            :url "twitter/tweet/_mtermvectors"
            :body body}))))
