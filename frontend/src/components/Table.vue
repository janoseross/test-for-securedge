<template>
  <div>
    <b-table
      striped
      hover
      :items="items"
      :fields="fields"
      sort-icon-left
    ></b-table>
  </div>
</template>

<script>
export default {
  components: {},
  props: {
    items: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      fields: [
        { key: "usd", sortable: true },
        { key: "eur", sortable: true },
        { key: "gbp", sortable: true },
        { key: "time", sortable: true },
      ],
    };
  },
};
</script>
