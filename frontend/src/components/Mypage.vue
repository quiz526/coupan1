<template>

<v-data-table
    :headers="headers"
    :items="mypage"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'Mypage',
    props: {
      value: Object,
      editMode: Boolean,
      isNew: Boolean
    },
    data: () => ({
        headers: [
            { text: "id", value: "id" },
            { text: "orderId", value: "orderId" },
            { text: "customerId", value: "customerId" },
            { text: "qty", value: "qty" },
            { text: "orderDate", value: "orderDate" },
            { text: "orderCancelDate", value: "orderCancelDate" },
            { text: "payAmt", value: "payAmt" },
            { text: "payDate", value: "payDate" },
            { text: "payCancelDate", value: "payCancelDate" },
            { text: "status", value: "status" },
        ],
        mypage : [],
    }),
    async created() {
      var temp = await axios.get(axios.backend + '/mypages')

      this.mypage = temp.data._embedded.mypages;

    },
    methods: {
    }
  }
</script>

