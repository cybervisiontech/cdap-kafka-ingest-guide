/*
 * Copyright © 2014 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package co.cask.cdap.guides.kafka; 

import co.cask.cdap.api.app.AbstractApplication;
import co.cask.cdap.api.dataset.lib.KeyValueTable;

/**
 * KafkaIngestionApp illustrates how to consume Kafka messages in a CDAP application using cdap-kafka-pack.
 */
public class KafkaIngestionApp extends AbstractApplication {
  static final String NAME = "KafkaIngestion";
  static final String STATS_TABLE_NAME = "kafkaCounter";
  static final String SERVICE_NAME = "KafkaStatsService";
  static final String OFFSET_TABLE_NAME = "kafkaOffsets";

  @Override
  public void configure() {
    setName(NAME);
    setDescription("Subscribe to Kafka Messages - Maintain overall count and size of messages received");
    createDataset(OFFSET_TABLE_NAME, KeyValueTable.class);
    createDataset(STATS_TABLE_NAME, KeyValueTable.class);
    addFlow(new KafkaIngestionFlow());
    addService(SERVICE_NAME, new KafkaStatsHandler());
  }
}