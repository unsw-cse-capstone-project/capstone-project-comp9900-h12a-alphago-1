<template>
  <div class="auction">
    <Header>
      <template v-if="this.hasLogin">
        <el-dropdown trigger="hover" @command="handleCommand" style="align-items: center" placement="bottom">
          <div class="user" >
            <el-badge v-if="parseInt(this.unread) !== 0" :value="this.unread" :max="99" class="item">
              <el-avatar :size="70" :src="avatar"></el-avatar>
            </el-badge>
            <el-avatar  v-else :size="70" :src="avatar"></el-avatar>

          </div>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="profile" icon="el-icon-user-solid"> My Profile</el-dropdown-item>
            <el-dropdown-item command="property"  icon="el-icon-house"> My Properties</el-dropdown-item>
            <el-dropdown-item command="auction" icon="el-icon-s-home"> My Auctions</el-dropdown-item>

            <el-dropdown-item command="notification"  icon="el-icon-bell">
              Notifications <el-badge v-show="parseInt(this.unread) !== 0" class="mark" :value="this.unread" style="padding:0;background-color: white"/>
            </el-dropdown-item>

            <el-dropdown-item command="logout" icon="el-icon-turn-off"> Log out</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </template>
      <template v-else>
        <div class="back-btn">
          <span
            id="back-btn"
            style="padding: 2px 5px; font-size: 20px"
            @click="back"
            >Back <i class="el-icon-refresh-right"></i
          ></span>
          <div class="bottom-line"></div>
        </div>
      </template>
    </Header>

    <el-row type="flex" justify="center">
      <el-col :span="24">

          <el-row
            type="flex"
            justify="center"
            style="background-color: #3b4c73; box-shadow: 0 2px 5px 2px #d5dbea"
          >
            <el-col :span="4">
              <el-select
                v-model="filter"
                style="margin: 10px 17%; width: 200px"
                placeholder="Select"
                @change="changeSearch"
              >
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </el-col>

            <el-col :span="6" >
              <div class="reg-btn" style="margin-top:15px;">
                                <span  id="reg-btn"
                                       style="

                                           color:white;
                                           padding:2px 5px;
                                           font-size:20px;
                                            /*position:absolute;*/
                                            "
                                       @click="goto('auctionmag')">
                                    <i class="el-icon-right"></i> Back</span>
              </div>
            </el-col>
          </el-row>
          <template v-if="!isEmpty">
            <el-row style="background-color: #85a1c5;text-align: center;">
              <span style="margin-top:5px; color:white;">Here are the auction histories.</span>
            </el-row>
          <el-row type="flex" justify="space-around">
            <div class="items">
              <ul>
                <li v-for="item in propList" :key="item.aid">
                  <div>
                    <el-row
                      :class="addStatusColor(item)"
                      style="height: 60px; padding: 0 10px"
                    >
                      <!--                                            <h5 >Stop At: {{ showTime(item.enddate) }}</h5>-->
                      <div
                        style="float: left; font-weight: bold; margin-top: 10px"
                      >
                        <span style="font-size: 30px; color: white">{{
                          getStatus(item)
                        }}</span>
                      </div>
                      <div
                        style="float: right; font-weight: bold; padding: 15px"
                      >
                        <span style="font-size: 15px">Highest Bid </span>
                        <span style="font-size: 20px"
                          >${{ item.currentBid | numFormat }}</span
                        >
                        <span style="font-size: 15px">AUD</span>
                      </div>
                    </el-row>
                    <el-row>
                      <el-carousel
                        :interval="5000"
                        arrow="always"
                        :height="cheight"
                        style="width: 100%"
                      >
                        <el-carousel-item
                          v-for="pic in item.photos"
                          :key="pic.id"
                        >
                          <img :src="pic" width="100%" height="100%" alt="" />
                        </el-carousel-item>
                      </el-carousel>
                    </el-row>
                    <div style="padding: 20px">
                      <h5>{{ decapitateAddress(item.address) }}</h5>
                      <el-row
                        type="flex"
                        justify="left"
                        style="margin: 10px 5%"
                      >
                        <el-col :span="4">
                          <i class="fas fa-bath" style="margin-right: 5px"></i> {{ item.bathroomNum}}
                        </el-col>
                        <el-col :span="4">
                          <i class="fas fa-bed" style="margin-right: 5px"></i> {{ item.bedroomNum }}
                        </el-col>
                        <el-col :span="4">
                          <i class="fas fa-car" style="margin-right: 5px"></i> {{ item.garageNum }}
                        </el-col>

                        <el-col :span="4">
                          <i class="fas fa-home" style="margin-right: 5px"></i> {{ item.area }} ㎡
                        </el-col>
                        <el-col :span="8">
                          <i class="fas fa-tags" style="margin-right: 5px"></i> {{ item.type }}
                        </el-col>
                      </el-row>
                      <el-row>
                        <el-col :span="12">
                          <i class="el-icon-info">
                            Start Date: {{ showTime(item.startdate) }}</i
                          >
                        </el-col>
                        <el-col :span="12">
                          <i class="el-icon-info">
                            End Date: {{ showTime(item.enddate) }}</i
                          >
                        </el-col>
                      </el-row>
                      <el-row type="flex" justify="space-around">
                        <div class="bid">
                          <span style="font-size: 15px">My Bid</span>
                          ${{ item.highestPrice | numFormat }}
                        </div>
                      </el-row>
                      <el-row type="flex" justify="space-around">
                        <template>
                          <!--                                                    <div class="bid2" :class="addStatusColor(item.status)"> {{ getStatus(item) }}</div>-->
                        </template>
                      </el-row>
                    </div>
                  </div>
                </li>
              </ul>
            </div>
          </el-row>
          </template>
        <template v-else>
          <div class="empty-label">
            <el-alert
                    title="You do not have any auction history."
                    type="info"
                    center
                    show-icon
                    :closable="false"
            >
            </el-alert>
          </div>
        </template>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Header from "@/components/Header.vue";
import { mapActions } from "vuex";
import dayjs from "dayjs";
import $ from "jquery";
import numFormat from "../utils/numFormat";

export default {
  name: "Auction",
  props: {
    cheight: {
      type: String,
      default: "320px",
    },
  },
  components: {
    Header,
  },
  data() {
    return {
      unread:'',
      id: "",
      filter: "all",
      isEmpty: false,
      hasLogin: false,
      filterPropertyList: [],
      originPropertyList: [
        // {
        //     aid:1,
        //     status:'S',
        //     bathroomNum:2,
        //     bedroomNum:1,
        //     garageNum:2,
        //     type:'Apartment',
        //     area:123,
        //     address:'2 Gearin Alley, Mascot, NSW 2020',
        //     photos:['https://anywhere-live.s3.amazonaws.com/property_images/554ff282-bfa4-49b9-bb34-9f0dfa32b373.jpg'],
        //     highestPrice: 1200000,
        //     currentBid:1200000,
        //     startdate: new Date(2019,10,10,12,10),
        //     enddate: new Date(2020,12,11,12,10),
        // }
        // ,{
        //     aid:2,
        //     status: 'F',
        //     address:'6 Eildon Street, Doncaster Victoria 3108',
        //     photos:['https://anywhere-live.s3.amazonaws.com/property_images/4e24ab60-188d-43cc-880a-7cedacc5d0eb.jpg'],
        //     highestPrice: 500000,
        //     currentBid:900000,
        //     reservedPrice: 10000,
        //     startdate: new Date(2019,10,10,12,10),
        //     enddate: new Date(2020,12,11,12,10),
        //     bathroomNum:2,
        //     bedroomNum:2,
        //     area:120,
        //     type:'House',
        //     garageNum:2,
        // },
        // {
        //     aid:3,
        //     status: 'S',
        //     address:'10 Craig Street, Keilor East Victoria 3033',
        //     photos:['https://anywhere-live.s3.amazonaws.com/property_images/ed3eda11-d83c-40f7-b70f-4cdf4824538a.jpg'],
        //     startdate: new Date(2021,10,10,12,10),
        //     enddate: new Date(2023,10,10,10,10),
        //     highestPrice: 1000000,
        //     currentBid:1200000,
        //     reservedPrice: 10000,
        //     bathroomNum:2,
        //     type:'House',
        //     bedroomNum:1,
        //     area:150,
        //     garageNum:2,
        // },

        // {
        //     aid:5,
        //     status: 'F',
        //     address:'123asd',
        //     photos:['',''],
        //     highestPrice: 123123,
        //     currentBid:22222,
        //     startdate:'',
        //     endddate:'',
        // },
        // {
        //     aid:6,
        //     status: 'S',
        //     address:'123asd',
        //     photos:['',''],
        //     highestPrice: 123123,
        //     currentBid:22222,
        //     startdate:'',
        //     endddate:'',
        // },
        // {
        //     aid:8,
        //     status: 'S',
        //     address:'123asd',
        //     photos:['',''],
        //     highestPrice: 123123,
        //     currentBid:22222,
        //     startdate:'',
        //     endddate:'',
        // },
      ],
      propList: [],
      options: [
        {
          value: "all",
          label: "All",
        },
        // {
        //     value: "R",
        //     label: "Not start",
        // },
        // {
        //     value: "A",
        //     label: "In process",
        // },
        {
          value: "S",
          label: "Sold",
        },
        {
          value: "F",
          label: "Passed In",
        },
      ],
      propList: [
        //     {
        //     status:'',
        //     address:'sdf13',
        //     bathroomNum:1,
        //     bedroomNum:2,
        //     garageNum:2,
        //     photos:['',''],
        // }
      ],
    };
  },
  created() {
    this.username = localStorage.getItem("username");
    // this.username = this.$store.state.username;
    if (this.username !== null) {
        this.hasLogin = true;
        this.avatar = localStorage.getItem("avatar");
        this.$axios
                .get("/auction/list/past")
                .then((response) => {
                  if (response.data.code === 200) {
                    this.originPropertyList = response.data.result;
                    this.propList = response.data.result;
                    // this.propInfo = this.originPropertyList[0];
                    if (this.originPropertyList.length === 0) {
                      this.isEmpty = true;
                      this.propList = this.originPropertyList;
                    }
                  } else if (response.data.code === 400) {
                    this.isEmpty = true;
                  }
                })
                .catch(function (error) {
                  console.log(error);
                });
        this.$axios
                .get('/notification/unread')
                .then(response => {
                  if (response.data.code === 200) {
                    this.unread = response.data.result;
                  }else if(response.data.code === 400){
                    this.$message.error(response.data.msg);
                  }else{
                    console.log(response.data.msg);
                  }
                })
                .catch(function (error) {
                  this.$message.error(error);
                });
    }
    else{
        this.$message.error("You should login first!");
        this.$router.push("/login");
    }

    // this.isEmpty = false;
    // this.propList = this.originPropertyList;
  },
  mounted() {
    $("#back-btn").hover(function (event) {
      $(this).stop().animate({ "margin-left": "10px" }, 300);
      $(this).next(".bottom-line").stop().animate({ width: "100px" }, 300);
    });
    $("#back-btn").mouseleave(function (event) {
      $(this).stop().animate({ "margin-left": "0" }, 300);
      $(this).next(".bottom-line").stop().animate({ width: "0" }, 300);
    });
  },
  methods: {
    ...mapActions(["logout"]),
    handleCommand(command) {
      switch (command) {
        case "profile":
          this.$router.push("/profile");
          break;
        case "auction":
          this.$router.push("/auctionmag");
          break;
        case "property":
          this.$router.push("/propmag");
          break;
        case "notification":
          this.$router.push("/notice");
          break;
        case "logout":
          this.$axios.post("/user/logout").then((response) => {
            if (response.status >= 200 && response.status < 300) {
              if (response.data.code === 200) {
                this.logout();
                this.$router.replace("/");
              } else {
                console.log(response.msg);
              }
            } else {
              console.log(response.msg);
            }
          });
          break;
        default:
          break;
      }
    },
    getStatus(item) {
      switch (item.status) {
        case "S":
          if(item.highestPrice ===item.currentBid){
            return 'Winner'
          }
          return "Sold";
          break;
        case "F":
          return "Passed In";
          break;
        default:
          break;
      }
    },

    changeSearch(value) {
      let filterPropertyList = [];
      if (value === "all") {
        filterPropertyList = this.originPropertyList;
      } else {
        filterPropertyList = this.originPropertyList.filter((e) => {
          return e.status === value;
        });
      }
      this.propList = filterPropertyList;
    },
    decapitateAddress(addr){
      if (addr.length<50){
        return addr;
      }
      else{
        return (addr.slice(0,50)+"...");
      }
    },
    userBid(latest, user) {
      let lp = parseInt(latest);
      let up = parseInt(user);
      if (user === "") {
        up = 0;
      }
      if (lp > up) {
        return "user-bid-bg-under";
      } else {
        return "user-bid-bg-above";
      }
    },
    addStatusColor(item) {
      switch (item.status) {
        case "S":
          if(item.highestPrice === item.currentBid){
            return "status-winner";
          }else{
            return "status-not-start1";
          }

          break;
        case "F":
          return "status-process1";
          break;
        default:
          break;
      }
    },
    // addStatusColor(status) {
    //     const colors = new Map([
    //         // ["R", "status-not-start"],
    //         // ["A", "status-process"],
    //         ["S", "status-success"],
    //         ["F", "status-failure"],
    //     ]);
    //     return colors.get(status);
    // },

    goto(name) {
      console.log(name);
      this.$router.push({ name: name });
    },
    back() {
      this.$router.go(-1);
    },

    goDetails(item) {
      this.$router.push({
        path: "/auction",
        query: {
          id: item.aid,
        },
      });
    },
    addZero(num){
      if (parseInt(num) >= 10) {
        return num;
      } else {
        return `0${num}`;
      }
    },
    showTime(time) {
      // let time = dayjs(day).format("YYYY-MM-DD HH:mm:ss");
      let MONTH = [
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "Jun",
        "Jul",
        "Aug",
        "Sep",
        "Oct",
        "Nov",
        "Dec",
      ];
      let day = dayjs(time).date();
      let mon = MONTH[dayjs(time).month()];
      let year = dayjs(time).year();
      let hour = dayjs(time).hour();
      let min = dayjs(time).minute();
      let formatTime = `${this.addZero(day)} ${mon} ${year}, ${this.addZero(hour)}:${this.addZero(min)}`;
      return formatTime;
    },
  },
};
</script>

<style scoped lang="scss">
    .user {
        display: flex;
        align-items: center;
        p {
            margin-left: 10px;
        }
    }
    .empty-label{
        /*font-size: 18px;*/
        text-align: center;
        margin: 10px;
    }
    .bid {
        width :90%;
        padding: 10px;
        text-align: center;
        font-size: 18px;
        font-weight: bold;
        color: #fff;
        background-color: #465c84;
        border-radius: 3px;
        margin-top:15px;
    }
    .bid2 {
        width :90%;
        padding: 3px;
        text-align: center;
        font-size: 12px;
        font-weight: bold;
        color: #fff;
        margin-top:5px;
        background-color: #133264;
        border-radius: 3px;
    }
    .userBid{
        width :45%;
        padding: 5px;
        text-align: center;
        font-size: 15px;
        font-weight: bold;
        color: #fff;
        background-color: #133264;
        border-radius: 5px;
    }
    .img{
        width: auto;
        height: auto;
        max-width: 100%;
        max-height: 100%;
    }

    .user-bid-bg-under{
        background: rgba(163, 61, 37, 0.65);
    }
    .user-bid-bg-above{
        background-color: rgba(16, 160, 112, 0.75);
    }

    .items{
        margin: 10px 10%;
        text-align:center;

    }
    .items ul{
        text-align:center;
        /*display: inline-block;*/
    }
    .items li{
        /*text-align:center;*/
        /*float:left;*/
        display:inline-block;
    }

    .items ul li {
        /*z-index: 1;*/
        width: 600px;
        height: 600px;
        /*padding: 10px 0;*/
        margin: 10px 20px;
        /*display: inline-block;*/
        position: relative;
        border: 1px solid rgba(20, 52, 105, 0.55);
        list-style-type:none;
        &:hover {
            /*cursor: pointer;*/
            /*transform: scale(1.02);*/
            background-color: rgba(193, 212, 240, 0.55);
            transition-duration: 0.3s;
        }
    }

    .status-failure {
        background-color: rgba(50, 17, 18, 0.67);
    }

    .status-process {
        background-color: #d9d978;
    }

    .status-success {
        background-color: rgba(135, 196, 116, 0.82);
    }

    .status-not-start {
        /*border-top: 15px solid #bcc8e6;*/
        background-color:  #8a97a6;
    }
    .back-btn{
        cursor: pointer;
        position:relative;
        /*border:1px solid #123123;*/
    }
    .back-btn .bottom-line{
        position: absolute;
        /*margin-left:40px;*/
        width: 0;
        height: 2px;
        left:0;
        bottom: -1px;
        background-color: #3b4c73;
    }

    .status-process1 {
        background-image: url("../assets/banner-bg-grey.png");
        /*background-color: #89c668;*/
    }
    .status-winner {
      background-image: url("../assets/banner-bg-yelo.png");
      /*background-color: #89c668;*/
    }

    .status-not-start1 {
        background-image: url("../assets/banner-bg-green.png");
        /*background-color: #f56c6c;*/
    }
    .reg-btn{
      cursor: pointer;
      position:relative;
      float:right;
      &:hover{
        transform:translateX(10px);
        transition-duration: 0.5s;
        border-bottom:1px solid white
      }
      /*float:left;*/
    }
</style>
