(ns elastics.core-test
  (:require [clojure.test :refer :all]
            [elastics.core :as core]))


(def body {:greet "hello"})


(deftest custom-test
  (testing "without body"
    (is (= (core/custom :get "twitter/tweet")
           {:method :get
            :url    "twitter/tweet"
            :body   nil})))

  (testing "with body"
    (is (= (core/custom body :get "twitter/tweet")
           {:method :get
            :url    "twitter/tweet"
            :body   body}))))


(deftest params-test
  (is (= (core/params {} {:q "user:kimchy"})
         {:query-params {:q "user:kimchy"}})))


(deftest extend-url-test
  (is (= (core/extend-url {:url "twitter/tweet"} "_source")
         {:url "twitter/tweet/_source"})))


(deftest extend-http-map-test
  (is (= (core/merge-http-map {:url "twitter/tweet"} {:as :transit+json})
         {:http-map {:as :transit+json}
          :url      "twitter/tweet"})))


(deftest build-test
  (let [conn {:url "http://localhost:9200"}]

    (testing "url"
      (is (= (:url (core/build {:url "twitter/tweet"} conn))
             "http://localhost:9200/twitter/tweet")))

    (testing "method"
      (is (= (:method (core/build {:url    "twitter/tweet"
                                   :method :get}
                                  conn))
             :get)))

    (testing "http-map"
      (is (= (:http-map (core/build {:url          "twitter/tweet"
                                     :method       :get
                                     :body         {:from 0, :size 10}
                                     :query-params {:q "user:kimchy"}}
                                    conn))
             {:query-params {:q "user:kimchy"}
              :as           :json
              :body         "{\"from\":0,\"size\":10}"})))))
