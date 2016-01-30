(ns elastics.index_test
  (:require [clojure.test :refer :all]
            [midje.sweet :refer :all]
            [elastics.index :as index]))



(def body {:greet "hello"})



(deftest create-test
  (testing "without body"
    (is (= (index/create "twitter")
           {:method :put
            :url    "twitter"
            :body   nil})))

  (testing "with body"
    (is (= (index/create body "twitter")
           {:method :put
            :url    "twitter"
            :body   body}))))



(deftest delete-test
  (is (= (index/delete "twitter")
         {:method :delete
          :url    "twitter"})))



(deftest get-test
  (is (= (index/get "twitter")
         {:method :get
          :url    "twitter"})))



(deftest exists-test
  (is (= (index/exists "twitter")
         {:method :head
          :url    "twitter"})))



(deftest close-test
  (is (= (index/close "twitter")
         {:method :put
          :url    "twitter/_close"})))



(deftest open-test
  (is (= (index/open "twitter")
         {:method :put
          :url    "twitter/_open"})))



(deftest put-mapping-test
  (testing "without type"
    (is (= (index/put-mapping body "twitter")
           {:method :put
            :url    "twitter"
            :body   body})))

  (testing "with type"
    (is (= (index/put-mapping body "twitter" "tweet")
           {:method :put
            :url    "twitter/_mapping/tweet"
            :body   body}))))



(deftest get-mapping-test
  (testing "without type"
    (is (= (index/get-mapping "twitter")
           {:method :get
            :url    "twitter/_mapping"})))

  (testing "with type"
    (is (= (index/get-mapping "twitter" "tweet")
           {:method :get
            :url    "twitter/_mapping/tweet"}))))



(deftest type-exists-test
  (is (= (index/type-exists "twitter" "tweet")
         {:method :head
          :url    "twitter/tweet"})))



(deftest aliases-test
  (is (= (index/aliases body)
         {:method :post
          :url    "_aliases"})))



(deftest add-alias-test
  (is (= (index/add-alias "twitter" "name")
         {:method :put
          :url    "twitter/_alias/name"})))



(deftest delete-alias-test
  (is (= (index/delete-alias "twitter" "name")
         {:method :delete
          :url    "twitter/_alias/name"})))



(deftest update-settings-test
  (testing "without index"
    (is (= (index/update-settings body)
           {:method :put
            :url    "_settings"
            :body   body})))

  (testing "with index"
    (is (= (index/update-settings body "twitter")
           {:method :put
            :url    "twitter/_settings"
            :body   body}))))



(deftest get-settings-test
  (is (= (index/get-settings "twitter")
         {:method :get
          :url    "twitter/_settings"})))



(deftest analyze-test
  (testing "without index"
    (is (= (index/analyze body)
           {:method :get
            :url    "_analyze"
            :body   body})))

  (testing "with index"
    (is (= (index/analyze body "twitter")
           {:method :get
            :url    "twitter/_analyze"
            :body   body}))))



(deftest put-template-test
  (is (= (index/put-template body "name")
         {:method :put
          :url    "_template/name"
          :body   body})))




(deftest delete-template-test
  (is (= (index/delete-template "name")
         {:method :delete
          :url    "_template/name"})))




(deftest get-template-test
  (is (= (index/get-template "name")
         {:method :get
          :url    "_template/name"})))



(deftest template-exists-test
  (is (= (index/template-exists "name")
         {:method :head
          :url    "_template/name"})))



(deftest put-warmer-test
  (testing "body and warmer"
    (is (= (index/put-warmer body "name")
           {:method :put
            :url    "_warmer/name"
            :body   body})))

  (testing "body, warmer, and index"
    (is (= (index/put-warmer body "twitter" "name")
           {:method :put
            :url    "twitter/_warmer/name"
            :body   body})))

  (testing "body, warmer, index, and type"
    (is (= (index/put-warmer body "twitter" "tweet" "name")
           {:method :put
            :url    "twitter/tweet/_warmer/name"
            :body   body}))))



(deftest delete-warmer-test
  (is (= (index/delete-warmer "twitter" "name")
         {:method :delete
          :url    "twitter/_warmer/name"})))



(deftest get-warmer-test
  (is (= (index/get-warmer "twitter" "name")
         {:method :get
          :url    "twitter/_warmer/name"})))



(deftest stats-test
  (testing "without index"
    (is (= (index/stats)
           {:method :get
            :url    "_stats"})))

  (testing "with index"
    (is (= (index/stats "twitter")
           {:method :get
            :url    "twitter/_stats"}))))



(deftest segments-test
  (testing "without index"
    (is (= (index/segments)
           {:method :get
            :url    "_segments"})))

  (testing "with index"
    (is (= (index/segments "twitter")
           {:method :get
            :url    "twitter/_segments"}))))



(deftest recovery-test
  (testing "without index"
    (is (= (index/recovery)
           {:method :get
            :url    "_recovery"})))

  (testing "with index"
    (is (= (index/recovery "twitter")
           {:method :get
            :url    "twitter/_recovery"}))))



(deftest shard-stores-test
  (testing "without index"
    (is (= (index/shard-stores)
           {:method :get
            :url    "_shard_stores"})))

  (testing "with index"
    (is (= (index/shard-stores "twitter")
           {:method :get
            :url    "twitter/_shard_stores"}))))




(deftest clear-cache-test
  (testing "without index"
    (is (= (index/clear-cache)
           {:method :post
            :url    "_cache/clear"})))

  (testing "with index"
    (is (= (index/clear-cache "twitter")
           {:method :post
            :url    "twitter/_cache/clear"}))))



(deftest flush-test
  (testing "without index"
    (is (= (index/flush)
           {:method :post
            :url    "_flush"})))

  (testing "with index"
    (is (= (index/flush "twitter")
           {:method :post
            :url    "twitter/_flush"}))))



(deftest refresh-test
  (testing "without index"
    (is (= (index/refresh)
           {:method :post
            :url    "_refresh"})))

  (testing "with index"
    (is (= (index/refresh "twitter")
           {:method :post
            :url    "twitter/_refresh"}))))



(deftest force-merge-test
  (testing "without index"
    (is (= (index/force-merge)
           {:method :post
            :url    "_forcemerge"})))

  (testing "with index"
    (is (= (index/force-merge "twitter")
           {:method :post
            :url    "twitter/_forcemerge"}))))



(deftest upgrade-test
  (testing "without index"
    (is (= (index/upgrade)
           {:method :post
            :url    "_upgrade"})))

  (testing "with index"
    (is (= (index/upgrade "twitter")
           {:method :post
            :url    "twitter/_upgrade"}))))

