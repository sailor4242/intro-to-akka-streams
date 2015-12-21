/*
 * Copyright 2015 Dennis Vriend
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.dnvriend.streams.stage

import akka.stream.scaladsl.Source
import akka.stream.testkit.scaladsl.TestSink
import com.github.dnvriend.streams.TestSpec

class MapStageTest extends TestSpec {
  /**
   * Transform this stream by applying the given function to each of the elements
   * as they pass through this processing step.
   *
   * - Emits when: the mapping function returns an element
   * - Backpressures when: downstream backpressures
   * - Completes when: upstream completes
   * - Cancels when: downstream cancels
   */

  "Map" should "transform the stream by applying the function to each element" in {
    Source(() ⇒ Iterator from 0)
      .take(3)
      .map(_ * 2)
      .runWith(TestSink.probe[Int])
      .request(3)
      .expectNext(0, 2, 4)
      .expectComplete()
  }
}