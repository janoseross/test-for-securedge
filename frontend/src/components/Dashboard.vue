<template>
  <div>
    <b-alert :show="!!errorMessage" variant="danger">
      {{ errorMessage }}
    </b-alert>
    <div class="pb-5">
      <Chart :exchangeRates="exchangeRatesCategorized" />
    </div>
    <div>
      <Table :items="exchangeRatesByRow" />
    </div>
  </div>
</template>

<script>
import { constants } from "@/utils/constants";
import dateFormat from "dateformat";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import Chart from "./Chart";
import Table from "./Table";

export default {
  components: {
    Chart,
    Table,
  },
  data() {
    return {
      connection: null,
      errorMessage: null,
      exchangeRatesCategorized: {
        usd: [],
        eur: [],
        gbp: [],
        time: [],
      },
      exchangeRatesByRow: [],
    };
  },
  mounted() {
    this.connect();
  },
  methods: {
    connect() {
      this.socket = new SockJS(constants.SERVER_ADDRESS);
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {},
        () => {
          this.stompClient.subscribe(constants.SUBSCRIBE_ADDRESS, (tick) => {
            let rawData = JSON.parse(tick.body);
            rawData.time = dateFormat(new Date(), constants.DATE_FORMAT);
            this.exchangeRatesByRow.unshift(rawData);
            this.exchangeRatesCategorized.usd.push(rawData.usd);
            this.exchangeRatesCategorized.eur.push(rawData.eur);
            this.exchangeRatesCategorized.gbp.push(rawData.gbp);
            this.exchangeRatesCategorized.time.push(rawData.time);
          });
        },
        () => {
          this.errorMessage = "Cannot connect to the server";
        }
      );
    },
  },
};
</script>

<style scoped></style>
