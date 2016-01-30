(ns elastics.helper-test
  (:require [clojure.test :refer :all]
            [elastics.helper :as helper]))


(def body {:greet "hello"})


(deftest file-test
  (testing "removing of nil"
    (is (= (helper/file "file1" nil "file2")
           "file1/file2")))

  (testing "nil"
    (is (= (helper/file nil)
           "")))

  (testing "empty string"
    (is (= (helper/file "")
           ""))))


(deftest create-url-test
  (is (= (helper/create-url "http://localhost:9200" "twitter/tweet")
         "http://localhost:9200/twitter/tweet")))


(deftest custom-test
  (testing "without body"
    (is (= (helper/custom :get "twitter/tweet")
           {:method :get
            :url    "twitter/tweet"
            :body   nil})))

  (testing "with body"
    (is (= (helper/custom body :get "twitter/tweet")
           {:method :get
            :url    "twitter/tweet"
            :body   body}))))


(deftest params-test
  (is (= (helper/params {} {:q "user:kimchy"})
         {:query-params {:q "user:kimchy"}})))


(deftest extend-url-test
  (is (= (helper/extend-url {:url "twitter/tweet"} "_source")
         {:url "twitter/tweet/_source"})))


(deftest extend-http-map-test
  (is (= (helper/merge-http-map {:url "twitter/tweet"} {:as :transit+json})
         {:http-map {:as :transit+json}
          :url      "twitter/tweet"})))


(deftest build-test
  (let [conn {:url "http://localhost:9200"}]

    (testing "url"
      (is (= (:url (helper/build {:url "twitter/tweet"} conn))
             "http://localhost:9200/twitter/tweet")))

    (testing "method"
      (is (= (:method (helper/build {:url    "twitter/tweet"
                                     :method :get}
                                    conn))
             :get)))

    (testing "http-map"
      (is (= (:http-map (helper/build {:url          "twitter/tweet"
                                       :method       :get
                                       :body         {:from 0, :size 10}
                                       :query-params {:q "user:kimchy"}}
                                      conn))
             {:query-params {:q "user:kimchy"}
              :as           :json
              :body         "{\"from\":0,\"size\":10}"})))))
