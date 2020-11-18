<template>
  <div>
    <highcharts :options="chartOptions"></highcharts>
  </div>
</template>

<script>
import { Chart } from "highcharts-vue";

export default {
  components: {
    highcharts: Chart,
  },
  props: {
    exchangeRates: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      chartOptions: {
        title: {
          text: "RON exchange rates",
        },
        xAxis: {
          categories: this.exchangeRates.time,
        },
        yAxis: {
          title: {
            text: "RON",
          },
        },
        series: [
          {
            name: "USD",
            data: this.exchangeRates.usd,
          },
          {
            name: "EUR",
            data: this.exchangeRates.eur,
          },
          {
            name: "GBP",
            data: this.exchangeRates.gbp,
          },
        ],
      },
    };
  },
};
</script>
