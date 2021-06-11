<script>
import { Line } from 'vue-chartjs'
import axios from 'axios'

export default {
  extends: Line,
  props: {},
  data: () => ({
            chartdata: {
                labels: [/* 'January', 'February', 'March', 'April', 'May', 'June', 'July' */],
                datasets: [
                    {
                        label: "Peso (kg)",
                        data: [/* 40, 39, 10, 40, 39, 80, 40 */],
                        backgroundColor: "transparent",
                        borderColor: "#f95738",
                        pointBackgroundColor: "white"
                    },
                    {
                        label: "Massa Muscular (%)",
                        data: [/* 98, 74, 14, 82, 57, 32, 68 */],
                        backgroundColor: "transparent",
                        borderColor: "#000314",
                        pointBackgroundColor: "white"
                    },
                    {
                        label: "Massa Gorda (%)",
                        data: [/* 15, 87, 53, 67, 17, 24, 30 */],
                        backgroundColor: "transparent",
                        borderColor: "#7189ff",
                        pointBackgroundColor: "white"
                    }
                ]
            },
            options: {
                scales: {
                    yAxes: [
                        {
                            ticks: {
                                beginAtZero: true
                            }
                        }
                    ]
                },
                responsive: true,
                maintainAspectRatio: false,
                title: {
                    /* display: true,
                    text: "Evolução Física",
                    fontSize: 20 */
                }
            }
  }),
  async mounted () {
    let resposta = await axios.get("http://localhost:4576/api/user/getHistoricoFisico",{headers: {'token': localStorage.getItem("token")}});
    resposta.data.forEach(x => {
          this.chartdata.labels.push(x.data);
          this.chartdata.datasets[0].data.push(x.peso);
          this.chartdata.datasets[1].data.push(x.m_muscular);
          this.chartdata.datasets[2].data.push(x.m_gorda);
        })
   /*axios
      .get("http://localhost:4576/api/user/getHistoricoFisico",{headers: {'token': localStorage.getItem("token")}})
      .then(resp => {
        resp.data.forEach(x => {
          this.chartdata.labels.push(x.data);
          this.chartdata.datasets[0].data.push(x.peso);
          this.chartdata.datasets[1].data.push(x.m_muscular);
          this.chartdata.datasets[2].data.push(x.m_gorda);
        })
        console.log(this.chartdata);
      })*/
    this.renderChart(this.chartdata, this.options)
  }
}
</script>