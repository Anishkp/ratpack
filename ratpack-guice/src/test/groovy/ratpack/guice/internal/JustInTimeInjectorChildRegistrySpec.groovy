/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ratpack.guice.internal

import com.google.inject.Guice
import ratpack.registry.Registry
import ratpack.server.ServerConfig
import spock.lang.Specification

import static ratpack.guice.Guice.justInTimeRegistry

class JustInTimeInjectorChildRegistrySpec extends Specification {

  static class Thing {}

  def "delegates to parent when no bound instance"() {
    when:
    def serverConfig = Mock(ServerConfig)
    def parent = Registry.single(ServerConfig, serverConfig)
    def injector = Guice.createInjector()
    def registry = parent.join(justInTimeRegistry(injector))

    then:
    registry.get(Thing) instanceof Thing
    registry.get(ServerConfig).is serverConfig
  }

}
