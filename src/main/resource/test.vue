<template>
  <div class="map-container">
 	<div class="main-data-container" >
           <datafilter  @params='showChildMsg'></datafilter>
  	</div>
          <!-- 如果右侧数据收起,map-container添加class="map-tfull",来调整地图缩放按钮和图例的位置 -->
          <!-- 右侧数据展示 begin-->
          <div class="main-data-container main-data-fright">
            <!-- 收起展开 begin-->
            <div id="hideRight" class="slider-back">
              <a href="javascript:;" class="careat"></a>
            </div>
            <!-- 收起展开 end -->
            <div id="rightModule" class="main-data-cnt" style="">
              <div class="main-data-ptnbox" v-show="countInfoShow">
                <!-- 统计信息 begin-->
                <div class="panel sf_panel mt10 sf_panel_ptn">
                  <div class="panel-head clearfix">
                    <h3 class="fl panel-head-title">
                      <span>统计信息</span>
                    </h3>
                    <a href="javascript:;" class="fr but-collap"></a>
                  </div>
                  <div class="panel-body">
                    <div class="nav-line-box nav-line-spd">
                      <ul>
                        <li :class="{on:POILi,forbid:poiCount==0}">
                          <a href="javascript:;" @click="countInfoSwitch('POI',poiCount)">
                          POI({{ poiCount }})</a>
                        </li>
                        <li :class="{on:landLi,forbid:getLandItemDetail.landItemDetail.length==0}">
                          <a href="javascript:;" @click="countInfoSwitch('land',getLandItemDetail.landItemDetail.length)">
                          	土地({{ getLandItemDetail.landItemDetail.length }})</a>
                        </li>
                        <li :class="{on:residenceLi,forbid:getNewHouseResidenceDetails.newHouseResidenceDetails.length+getSecondHouseResidenceDetails.secondHouseResidenceDetails.length==0}">
                          <a href="javascript:;" @click="countInfoSwitch('residence',getNewHouseResidenceDetails.newHouseResidenceDetails.length+getSecondHouseResidenceDetails.secondHouseResidenceDetails.length)">
                          	住宅({{ newHouseTotal+secHouseTotal}})</a>
                        </li>
                        <li :class="{on:businessLi,forbid:getNewHouseShopDetails.newHouseShopDetails.length+getSecondHouseShopDetails.secondHouseShopDetails.length
                          	+getNewHouseOfficeDetails.newHouseOfficeDetails.length+getSecondHouseOfficeDetails.secondHouseOfficeDetails.length==0}">
                          <a href="javascript:;" @click="countInfoSwitch('business',getNewHouseShopDetails.newHouseShopDetails.length+getSecondHouseShopDetails.secondHouseShopDetails.length
                          	+getNewHouseOfficeDetails.newHouseOfficeDetails.length+getSecondHouseOfficeDetails.secondHouseOfficeDetails.length)">
                          	商业({{ shopTotal+officeTotal}})</a>
                        </li>
                      </ul>
                    </div>
                    <!-- POI begin-->
                    <div class="tablist tab-POI" style="" v-show="POIShow">
                      <!-- 选择条件 begin-->
                     <div class="from-row clearfix form-select-row mt10">
                        <div class="clearfix div_par2">
                         <div class="form-input fl form-input-sm pad_r20">
                            <el-select v-model="situatPlann" size="mini" @change="classesChange">
                                <el-option
                                  v-for="item in situatPlanns"
                                  :key="item"
                                  :label="item"
                                  :value="item">
                                </el-option>
                            </el-select>
                          </div>
                          <div class="form-input fl form-input-sm pad_r20">
                            <el-select v-model="element" size="mini" @change="poiTypesChange">
                                <el-option
                                  v-for="item in elements"
                                  :key="item"
                                  :label="item"
                                  :value="item">
                                </el-option>
                            </el-select>
                          </div>
                        </div>
                      </div>
                      <!-- 选择条件 end-->
                      <!-- POI begin-->
                      <div class="list-cont nice-scroll">
                        <!-- 地铁 begin-->
                        <div class="sub_sf_panel sub_sf_panel_wbg mt05" v-if="item.cname==element" v-for="item in getPoiDetailsInfo.poiDetailsInfo">
                          <div class="panel-head clearfix">
                            <h3 class="fl panel-head-title">
                              <span>{{ item.sType }}<span v-if="item.sTag">-{{ item.sTag }}</span>
                                <em>({{ (item.boardDetailsResourceDtoList||[]).length }})</em>
                              </span>
                            </h3>
                          </div>
                          <div class="panel_body">
                            <ul class="bk-list no-margin">
                              <li v-for="poiDetailsInfo in item.boardDetailsResourceDtoList" @mouseover="poiMouseover(poiDetailsInfo)" @mouseout="poiMouseout(poiDetailsInfo)">
                                <div class="buld-tit clearfix">
                                  <span class="fl text-nowrap b-tit tw01" :title="poiDetailsInfo.sName">{{ poiDetailsInfo.sName }}</span>
                                  <span class="sf-lable-red fr">{{ poiDetailsInfo.fScore }}分</span>
                                  <span class="sf-lable-green fr">{{ poiDetailsInfo.sTag }}</span>
                                </div>
                                <div class="buld_infor clearfix">
                                  <div class="comp white-b" v-if="item.sType=='地铁'">途经线路：{{ poiDetailsInfo.sAddress?poiDetailsInfo.sAddress:"--" }}</div>
                                  <div class="comp white-b" v-else-if="item.sType=='公交'">途经公交：{{ poiDetailsInfo.sAddress?poiDetailsInfo.sAddress:"--" }}</div>
                                  <div class="comp white-b" v-else>地址：{{ poiDetailsInfo.sAddress?poiDetailsInfo.sAddress:"--" }}</div>
                                </div>
                              </li>
                              <!-- <li>
                                <div class="buld-tit clearfix">
                                  <span class="fl text-nowrap b-tit tw01">郭公庄地铁站</span>
                                  <span class="sf-lable-red fr">5分</span>
                                  <span class="sf-lable-green fr">住宅</span>
                                </div>
                                <div class="buld_infor clearfix">
                                  <div class="comp white-b">途径路线：北京南站</div>
                                </div>
                              </li> -->
                            </ul>
                          </div>
                        </div>
                        <!-- 地铁 end-->
                        <!-- 公交 begin-->
                        <!-- <div class="sub_sf_panel sub_sf_panel_wbg mt05">
                          <div class="panel-head clearfix">
                            <h3 class="fl panel-head-title">
                              <span>公交
                                <em>(2)</em>
                              </span>
                            </h3>
                          </div>
                          <div class="panel_body">
                            <ul class="bk-list no-margin">
                              <li>
                                <div class="buld-tit clearfix">
                                  <span class="fl text-nowrap b-tit tw01">八王坟动</span>
                                  <span class="sf-lable-red fr">5分</span>
                                  <span class="sf-lable-green fr">住宅</span>
                                </div>
                                <div class="buld_infor clearfix">
                                  <div class="comp white-b">途径路线：391,301</div>
                                </div>
                              </li>
                              <li>
                                <div class="buld-tit clearfix">
                                  <span class="fl text-nowrap b-tit tw01">八王坟动</span>
                                  <span class="sf-lable-red fr">5分</span>
                                  <span class="sf-lable-green fr">住宅</span>
                                </div>
                                <div class="buld_infor clearfix">
                                  <div class="comp white-b">途径路线：391,301</div>
                                </div>
                              </li>
                            </ul>
                          </div>
                        </div> -->
                        <!-- 公交 end-->
                      </div>
                    </div>
                    <!-- POI end-->
                    <!-- 土地 begin-->
                    <div class="tablist" style="" v-show="landShow">
                      <div class="list-cont nice-scroll">
                        <div class="sub_sf_panel sub_sf_panel_wbg mt05">
                          <div class="panel_body">
                            <ul class="bk-list ld-list no-margin">
                              <li v-for="item in getLandItemDetail.landItemDetail" @mouseover="landMouseover(item)" @mouseout="landMouseout(item)">
                                <div class="buld-tit clearfix">
                                  <span class="fl text-nowrap b-tit tw01" @click="countInfoDetailSwitch('landDetail','','',item)" :title="item.parcelName?item.parcelName:'--'">{{ item.parcelName?item.parcelName:"--" }}</span>

                                  <span class="sf-lable-green fr" v-if="item.iDealStatus==2">已成交</span>
                                  <span class="sf-lable-green fr" v-if="item.iDealStatus==1">未成交</span>
                                  <span class="sf-lable-green fr" v-if="item.iDealStatus==3">流拍</span>
                                  <span class="sf-lable-green fr" v-if="item.iDealStatus==4 || item.iDealStatus==5 || item.iDealStatus==6">交易变更</span>
                                </div>
                                <div class="buld_infor clearfix">
                                  <div class="white-b">用地性质：
                                    <em class="font12">{{ item.conforming?item.conforming:"--" }}</em>
                                  </div>
                                  <div class="white-b" v-if="item.iDealStatus==2">成交时间：
                                    <em class="font12">{{ item.completionDate?item.completionDate:"--" }}</em>
                                  </div>
                                  <div class="white-b" v-if="item.iDealStatus==1 || item.iDealStatus==3 || item.iDealStatus==4 || item.iDealStatus==5 || item.iDealStatus==6">截止时间：
                                    <em class="font12">{{ item.endDate?item.endDate:"--" }}</em>
                                  </div>
                                  <div class="white-b">规划建面：
                                    <em class="font12">{{ item.planningArea?item.planningArea:"--" }}</em>&nbsp;㎡</div>
                                  <div class="white-b">楼面均价：
	                                <em class="font12" v-if="item.dealStatus=='已成交'">{{ item.closingFloorPrice!=0?item.closingFloorPrice:"--" }}</em>
	                                <em class="font12" v-if="item.dealStatus=='未成交' || item.dealStatus=='流拍' || item.dealStatus==4 || item.dealStatus==5 || item.dealStatus==6">{{ item.initialFloorPrice!=0?item.initialFloorPrice:"--" }}</em>
	                                &nbsp;元/㎡</div>
                                  <!-- <div class="comp white-b">开发公司：
                                    <em class="font12">北京亿恒房产开发有限公司</em>
                                  </div> -->
                                </div>
                              </li>
                              <!-- <li>
                                <div class="buld-tit clearfix">
                                  <span class="fl text-nowrap b-tit tw01">土地名称名称名称土地名称名称名称</span>

                                  <span class="sf-lable-green fr">已成交</span>
                                </div>
                                <div class="buld_infor clearfix">
                                  <div class="white-b">用地性质：
                                    <em class="font12">综合用地(含住宅)</em>
                                  </div>
                                  <div class="white-b">建筑年代：
                                    <em class="font12">2017-10-20</em>
                                  </div>
                                  <div class="white-b">建筑面积：
                                    <em class="font12">200000</em>&nbsp;㎡</div>
                                  <div class="white-b">租售价格：
                                    <em class="font12">20000</em>&nbsp;元/㎡</div>
                                  <div class="comp white-b">开发公司：
                                    <em class="font12">北京亿恒房产开发有限公司</em>
                                  </div>
                                </div>
                              </li> -->
                            </ul>
                          </div>
                        </div>

                      </div>

                    </div>
                    <!-- 土地 end-->
                    <!-- 住宅 begin-->
                    <div class="tablist" style="" v-show="residenceShow" v-citymap-loading.standard="loadhouse">
                      <div class="list-cont nice-scroll">
                        <!-- 新房 begin-->
                        <div class="sub_sf_panel sub_sf_panel_wbg mt05">
                          <div class="panel-head clearfix">
                            <h3 class="fl panel-head-title">
                              <span>新房
                                <!--<em>({{ newHouseTotal }})</em>-->
                              </span>
                            </h3>
                          </div>
                          <div class="panel_body">
                            <ul class="bk-list ld-list no-margin">
                              <!-- <li v-for="item in getNewHouseResidenceDetails.newHouseResidenceDetails" @mouseover="houseMouseOver(item,'newHouse_point_')" @mouseout="houseMouseOut(item,'newHouse_point_')"> -->
                              <li v-for="item in getNewHouseResidenceDetails.newHouseResidenceDetails" @mouseover="houseMouseOver(item,'newHouse_point_')" @mouseout="houseMouseOut(item,'newHouse_point_')">
                                <div class="buld-tit clearfix">
                                  <span class="fl text-nowrap b-tit tw01" @click="countInfoDetailSwitch('residenceDetail','',item.projectId)" :title="item.projectName">{{ item.projectName }}</span>

                                  <span class="sf-lable-green fr for_sale" v-if="item.saleStatus=='在售'">在售</span>
                                  <span class="sf-lable-green fr not_for_sale" v-if="item.saleStatus=='未售'">未售</span>
                                  <span class="sf-lable-green fr sale_out" v-if="item.saleStatus=='售罄'">售罄</span>
                                  <!-- <span class="sf-lable-green fr rent_ing" v-if="item.saleStatus=='在租'">在租</span>
                                  <span class="sf-lable-green fr rent_ing" v-if="item.saleStatus=='租售'">租售</span> -->
                                </div>
                                <div class="buld_infor clearfix">
                                  <div class="white-b">楼盘均价：
                                    <em class="font12">{{ item.dealUnitPrice?item.dealUnitPrice.toFixed(2):'--' }}</em>&nbsp;元/㎡</div>
                                  <div class="white-b">近1年销售额：
                                    <em class="font12">{{ item.roomAmout12?((item.roomAmout12/100000000).toFixed(2)):'--' }}</em>&nbsp;亿元</div>
                                  <div class="white-b">物业类别：
                                    <em class="font12">{{ item.propertyType }}</em>
                                  </div>
                                  <div class="white-b">开盘时间：
                                    <em class="font12">{{ item.dSaleDate }}</em>年</div>
                                  <div class="white-b">容积率：
                                    <em class="font12">{{ item.fDimension }}</em></div>
                                  <div class="white-b">建筑面积：
                                    <em class="font12">{{ item.structureArea }}</em>&nbsp;㎡</div>
                                  <div class="white-b">项目特色：
                                    <em class="font12">{{ item.characteristic }}</em>
                                  </div>
                                  <div class="comp white-b">开发公司：
                                    <em class="font12">{{ item.developer }}</em>
                                  </div>
                                </div>
                              </li>
                              <!-- <li>
                                <div class="buld-tit clearfix">
                                  <span class="fl text-nowrap b-tit tw01">土地名称名称名称土地名称名称名称</span>

                                  <span class="sf-lable-green fr">已成交</span>
                                </div>
                                <div class="buld_infor clearfix">
                                  <div class="white-b">物业类别：
                                    <em class="font12">住宅</em>
                                  </div>
                                  <div class="white-b">建筑年代：
                                    <em class="font12">2017</em>年</div>
                                  <div class="white-b">建筑面积：
                                    <em class="font12">200000</em>&nbsp;㎡</div>
                                  <div class="white-b">楼盘均价：
                                    <em class="font12">20000</em>&nbsp;元/㎡</div>
                                  <div class="comp white-b">开发公司：
                                    <em class="font12">北京亿恒房产开发有限公司</em>
                                  </div>
                                </div>
                              </li> -->
                            </ul>
                          </div>
                        </div>
                        <!-- 新房 end-->
                        <!-- 二手房 begin-->
                        <div class="sub_sf_panel sub_sf_panel_wbg mt05">
                          <div class="panel-head clearfix">
                            <h3 class="fl panel-head-title">
                              <span>二手房
                                <!--<em>({{ secHouseTotal }})</em>-->
                              </span>
                            </h3>
                          </div>
                          <div class="panel_body">
                            <ul class="bk-list ld-list no-margin">
                             <!--  <li v-for="item in getSecondHouseResidenceDetails.secondHouseResidenceDetails"  @mouseover="houseMouseOver(item,'esfHouse_point_')" @mouseout="houseMouseOut(item,'esfHouse_point_')"> -->
                              <li v-for="item in getSecondHouseResidenceDetails.secondHouseResidenceDetails" @mouseover="houseMouseOver(item,'esfHouse_point_')" @mouseout="houseMouseOut(item,'esfHouse_point_')">
                                <div class="buld-tit clearfix">
                                  <span class="fl text-nowrap b-tit tw01" @click="countInfoDetailSwitch('residenceDetail','secondHouse',item.projectId,item)" :title="item.projectName">{{ item.projectName }}</span>

                                  <span class="sf-lable-green fr for_sale" v-if="item.communityGrade">{{ item.communityGrade }}</span>
                                  <!-- <span class="sf-lable-green fr not_for_sale" v-if="item.saleStatus=='未售'">未售</span>
                                  <span class="sf-lable-green fr sale_out" v-if="item.saleStatus=='售罄'">售罄</span> -->
                                  <!-- <span class="sf-lable-green fr rent_ing" v-if="item.saleStatus=='在租'">在租</span>
                                  <span class="sf-lable-green fr rent_ing" v-if="item.saleStatus=='租售'">租售</span> -->
                                </div>
                                <div class="buld_infor clearfix">
                                  <div class="white-b">物业类别：
                                    <em class="font12">{{ item.propertyType }}</em>
                                  </div>
                                  <div class="white-b">建筑年代：
                                    <em class="font12">{{ item.structureAge }}</em>年</div>
                                  <div class="white-b">绿化率：
                                    <em class="font12">{{ item.greeningRate }}</em>%</div>
                                  <div class="white-b" v-if="item.fDimension && item.fdimension !=0">容积率：
                                    <em class="font12" >{{ item.fDimension }}</em></div>
                                  <div class="white-b">建筑面积：
                                    <em class="font12">{{ item.structureArea }}</em>&nbsp;㎡</div>
                                  <div class="white-b">小区均价：
                                    <em class="font12">{{ item.unitPrice }}</em>&nbsp;元/㎡</div>
                                  <div class="white-b">项目特色：
                                    <em class="font12">{{ item.characteristic }}</em></div>
                                  <div class="comp white-b">开发公司：
                                    <em class="font12">{{ item.developer }}</em>
                                  </div>
                                </div>
                              </li>
                              <!-- <li>
                                <div class="buld-tit clearfix">
                                  <span class="fl text-nowrap b-tit tw01">土地名称名称名称土地名称名称名称</span>

                                  <span class="sf-lable-green fr">已成交</span>
                                </div>
                                <div class="buld_infor clearfix">
                                  <div class="white-b">物业类别：
                                    <em class="font12">住宅</em>
                                  </div>
                                  <div class="white-b">建筑年代：
                                    <em class="font12">2017</em>年</div>
                                  <div class="white-b">建筑面积：
                                    <em class="font12">200000</em>&nbsp;㎡</div>
                                  <div class="white-b">楼盘均价：
                                    <em class="font12">20000</em>&nbsp;元/㎡</div>
                                  <div class="comp white-b">开发公司：
                                    <em class="font12">北京亿恒房产开发有限公司</em>
                                  </div>
                                </div>
                              </li> -->
                            </ul>
                          </div>
                        </div>
                        <!-- 二手房 end-->
                           <!-- 加载更多 begin-->
	                    <div class="load-more" v-show="(getNewHouseResidenceDetails.newHouseResidenceDetails.length+getSecondHouseResidenceDetails.secondHouseResidenceDetails.length)<(newHouseTotal+secHouseTotal)" @click="initMoreHouse('house')">
	                      <p>加载更多
	                        <em class="el-icon-arrow-down"></em>
	                      </p>
	                    </div>
                    <!-- 加载更多 end-->

                      </div>

                    </div>
                    <!-- 住宅 end-->
                    <!-- 商铺 begin-->
                    <div class="tablist" style="" v-show="businessShow" v-citymap-loading.standard="loadhouse">
                      <div class="list-cont nice-scroll">
                        <!-- 商铺 begin-->
                        <div class="sub_sf_panel sub_sf_panel_wbg mt05">
                          <div class="panel-head clearfix">
                            <h3 class="fl panel-head-title">
                              <span>商铺
                                <!--<em>({{ shopTotal }})</em>-->
                              </span>
                            </h3>
                          </div>
                          <div class="panel_body">
                            <ul class="bk-list ld-list no-margin">
                              <!-- <li v-for="item in getNewHouseShopDetails.newHouseShopDetails"  @mouseover="houseMouseOver(item,'shops_point_')" @mouseout="houseMouseOut(item,'shops_point_')"> -->
                              <li v-for="item in getNewHouseShopDetails.newHouseShopDetails" @mouseover="houseMouseOver(item,'shops_point_')" @mouseout="houseMouseOut(item,'shops_point_')">
                                <div class="buld-tit clearfix">
                                  <span class="fl text-nowrap b-tit tw01" @click="countInfoDetailSwitch('businessDetail','',item.projectId)" :title="item.projectName">{{ item.projectName }}</span>

                                  <span class="sf-lable-green fr for_sale" v-if="item.saleStatus=='在售'">在售</span>
                                  <span class="sf-lable-green fr not_for_sale" v-if="item.saleStatus=='未售'">未售</span>
                                  <span class="sf-lable-green fr sale_out" v-if="item.saleStatus=='售罄'">售罄</span>
                                  <!-- <span class="sf-lable-green fr rent_ing" v-if="item.saleStatus=='在租'">在租</span>
                                  <span class="sf-lable-green fr rent_ing" v-if="item.saleStatus=='租售'">租售</span> -->
                                </div>
                                <div class="buld_infor clearfix">
                                  <div class="white-b">出售类型：
                                    <em class="font12">{{ item.shopOnSaleType }}</em></div>
                                  <div class="white-b">建筑年代：
                                  <em class="font12">{{ item.structureAge||"--" }}</em>年</div>
                                  <div class="white-b">建筑面积：
                                    <em class="font12">{{ item.structureArea||"--" }}</em>&nbsp;㎡</div>
                                  <div class="white-b">租售价格：
                                    <em class="font12">{{ item.unitPrice||"--" }}</em>&nbsp;{{ item.priceType }}</div>
                                  <div class="comp white-b">开发公司：
                                    <em class="font12">{{ item.developer||"--" }}</em>
                                  </div>
                                </div>
                              </li>
                              <li v-for="item in getSecondHouseShopDetails.secondHouseShopDetails" @mouseover="houseMouseOver(item,'shops_point_')" @mouseout="houseMouseOut(item,'shops_point_')">
                                <div class="buld-tit clearfix">
                                  <span class="fl text-nowrap b-tit tw01" @click="countInfoDetailSwitch('businessDetail','secondHouse',item.projectId,item)" :title="item.projectName">{{ item.projectName }}</span>

                                  <span class="sf-lable-green fr for_sale" v-if="item.communityGrade">{{ item.communityGrade }}</span>
                                  <!-- <span class="sf-lable-green fr not_for_sale" v-if="item.saleStatus=='未售'">未售</span>
                                  <span class="sf-lable-green fr sale_out" v-if="item.saleStatus=='售罄'">售罄</span> -->
                                  <!-- <span class="sf-lable-green fr rent_ing" v-if="item.saleStatus=='在租'">在租</span>
                                  <span class="sf-lable-green fr rent_ing" v-if="item.saleStatus=='租售'">租售</span> -->
                                </div>
                                <div class="buld_infor clearfix">
                                  <div class="white-b">出售类型：
                                    <em class="font12">{{ item.shopOnSaleType }}</em></div>
                                  <div class="white-b">建筑年代：
                                  <em class="font12">{{ item.structureAge||"--" }}</em>年</div>
                                  <div class="white-b">建筑面积：
                                    <em class="font12">{{ item.structureArea ||"--"}}</em>&nbsp;㎡</div>
                                  <div class="white-b">租售价格：
                                    <em class="font12">{{ item.unitPrice||"--" }}</em>&nbsp;元/㎡</div>
                                  <div class="comp white-b">开发公司：
                                    <em class="font12">{{ item.developer||"--"}}</em>
                                  </div>
                                </div>
                              </li>
                              <!-- <li>
                                <div class="buld-tit clearfix">
                                  <span class="fl text-nowrap b-tit tw01">土地名称名称名称土地名称名称名称</span>

                                  <span class="sf-lable-green fr">已成交</span>
                                </div>
                                <div class="buld_infor clearfix">
                                  <div class="white-b">出售类型:
                                    <em class="font12">住宅</em>
                                  </div>
                                  <div class="white-b">建筑年代：
                                    <em class="font12">2017</em>年</div>
                                  <div class="white-b">建筑面积：
                                    <em class="font12">20.12</em>&nbsp;万㎡</div>
                                  <div class="white-b">租售价格:
                                    <em class="font12">20000</em>&nbsp;元/㎡</div>
                                  <div class="comp white-b">开发公司：
                                    <em class="font12">北京亿恒房产开发有限公司</em>
                                  </div>
                                </div>
                              </li> -->
                            </ul>
                          </div>
                        </div>
                        <!-- 商铺 end-->
                        <!-- 写字楼 begin-->
                        <div class="sub_sf_panel sub_sf_panel_wbg mt05">
                          <div class="panel-head clearfix">
                            <h3 class="fl panel-head-title">
                              <span>写字楼
                                <!-- <em>({{ officeTotal }})</em> -->
                              </span>
                            </h3>
                          </div>
                          <div class="panel_body">
                            <ul class="bk-list ld-list no-margin">
                              <!-- <li v-for="item in getNewHouseOfficeDetails.newHouseOfficeDetails" @mouseover="houseMouseOver(item,'shops_point_')" @mouseout="houseMouseOut(item,'shops_point_')"> -->
                              <li v-for="item in getNewHouseOfficeDetails.newHouseOfficeDetails" @mouseover="houseMouseOver(item,'shops_point_')" @mouseout="houseMouseOut(item,'shops_point_')">
                                <div class="buld-tit clearfix">
                                  <span class="fl text-nowrap b-tit tw01" @click="countInfoDetailSwitch('officeDetail','',item.projectId)" :title="item.projectName">{{ item.projectName }}</span>

                                  <span class="sf-lable-green fr for_sale" v-if="item.saleStatus=='在售'">在售</span>
                                  <span class="sf-lable-green fr not_for_sale" v-if="item.saleStatus=='未售'">未售</span>
                                  <span class="sf-lable-green fr sale_out" v-if="item.saleStatus=='售罄'">售罄</span>
                                  <!-- <span class="sf-lable-green fr rent_ing" v-if="item.saleStatus=='在租'">在租</span>
                                  <span class="sf-lable-green fr rent_ing" v-if="item.saleStatus=='租售'">租售</span> -->
                                </div>
                                <div class="buld_infor clearfix">
                                  <div class="white-b">建筑面积：
                                    <em class="font12">{{ item.structureArea ||"--"}}</em>&nbsp;㎡</div>
                                  <div class="white-b">标准层面积：
                                    <em class="font12">{{ item.officeStandardArea||"--" }}</em>&nbsp;㎡</div>
                                  <div class="white-b">建筑年代：
                                  <em class="font12">{{ item.structureAge ||"--"}}</em>年</div>
                                  <div class="white-b">租售价格：
                                    <em class="font12">{{ item.unitPrice||"--" }}</em>&nbsp;{{ item.priceType }}</div>
                                  <div class="comp white-b">开发公司：
                                    <em class="font12">{{ item.developer||"--" }}</em>
                                  </div>
                                </div>
                              </li>
                              <li v-for="item in getSecondHouseOfficeDetails.secondHouseOfficeDetails" @mouseover="houseMouseOver(item,'shops_point_')" @mouseout="houseMouseOut(item,'shops_point_')">
                                <div class="buld-tit clearfix">
                                  <span class="fl text-nowrap b-tit tw01" @click="countInfoDetailSwitch('officeDetail','secondHouse',item.projectId,item)" :title="item.projectName">{{ item.projectName }}</span>

                                  <span class="sf-lable-green fr for_sale" v-if="item.communityGrade">{{ item.communityGrade }}</span>
                                  <!-- <span class="sf-lable-green fr not_for_sale" v-if="item.saleStatus=='未售'">未售</span>
                                  <span class="sf-lable-green fr sale_out" v-if="item.saleStatus=='售罄'">售罄</span> -->
                                  <!-- <span class="sf-lable-green fr rent_ing" v-if="item.saleStatus=='在租'">在租</span>
                                  <span class="sf-lable-green fr rent_ing" v-if="item.saleStatus=='租售'">租售</span> -->
                                </div>
                                <div class="buld_infor clearfix">
                                  <div class="white-b">建筑面积：
                                    <em class="font12">{{ item.structureArea||"--" }}</em>&nbsp;㎡</div>
                                  <div class="white-b">标准层面积：
                                    <em class="font12">{{ item.officeStandardArea||"--" }}</em>&nbsp;㎡</div>
                                  <div class="white-b">建筑年代：
                                  <em class="font12">{{ item.structureAge||"--" }}</em>年</div>
                                  <div class="white-b">租售价格：
                                    <em class="font12">{{ item.unitPrice||"--" }}</em>&nbsp;元/㎡</div>
                                  <div class="comp white-b">开发公司：
                                    <em class="font12">{{ item.developer||"--" }}</em>
                                  </div>
                                </div>
                              </li>
                            </ul>
                          </div>
                        </div>
                        <!-- 写字楼 end-->
                         <!-- 加载更多 begin-->
	                     <div class="load-more" v-show="(getNewHouseShopDetails.newHouseShopDetails.length+getSecondHouseShopDetails.secondHouseShopDetails.length
	                          	+getNewHouseOfficeDetails.newHouseOfficeDetails.length+getSecondHouseOfficeDetails.secondHouseOfficeDetails.length)<(shopTotal+officeTotal)" @click="initMoreHouse('shop')">
	                      <p>加载更多
	                        <em class="el-icon-arrow-down"></em>
	                      </p>
	                    </div>
	                    <!-- 加载更多 end-->
                      </div>

                    </div>
                    <!-- 商铺 end-->
                  </div>
                </div>
                <!-- 排行榜 end-->
              </div>
	            <!--弹出地块列表详情 start-->
	            <div class="main-data-cnt" style="" v-show="landDetailShow">
	            	<div class="h-tit clearfix h-tit-b">
	            		<div class="clearfix">
	            			<h2 class="tit-text fl ft_bold">地块详情</h2>
		                <a href="javascript:;" class="btn-back fr cornerbg mt10">
		                  <span class="icon-back" @click="countInfoDetailSwitch('countInfo')"></span>
		                </a>
	            		</div>
	                <h2 class="clearfix tit-text mt05 tit-text1" :title="landDetailInfos.parcelName">
	                {{ landDetailInfos.parcelName?(landDetailInfos.parcelName.length>45?(landDetailInfos.parcelName.substring(0,45)+"..."):landDetailInfos.parcelName):"--"}}
	                <span class="sf-lable-green fr" v-if="landDetailInfos.iDealStatus==2">已成交</span>
	                <span class="sf-lable-green fr" v-if="landDetailInfos.iDealStatus==1">未成交</span>
	                <span class="sf-lable-green fr" v-if="landDetailInfos.iDealStatus==3">流拍</span>
	                <span class="sf-lable-green fr" v-if="landDetailInfos.iDealStatus==4 || landDetailInfos.iDealStatus==5 || landDetailInfos.iDealStatus==6">交易变更</span>
	                </h2>
	              </div>
		            <div class="main-data-ptnbox nice-scroll main-data-ptnbox-b">
		            	<!-- 基本信息 begin-->
			            <div class="panel sf_panel">
			              	<div class="panel-head clearfix">
			                <h3 class="panel-head-title">
			                  <span>基本信息</span>
			                </h3>
			              </div>
			              <div class="panel-body">
			              	<ul class="bk-list ld-list no-margin">
			                  <li>
			                  	<!-- <div class="buld-tit clearfix">
	                          <span class="fl text-nowrap b-tit tw01">土地名称名称名称土地名称名称名称</span>
	                          <span class="sf-lable-green fr">已成交</span>
	                        </div> -->
			                    <div class="buld_infor clearfix">
			                      <div class="white-b" v-if="landDetailInfos.parcelPlace">土地位置：
			                        <em class="font12">{{ landDetailInfos.parcelPlace }}</em>
			                      </div>
			                      <div class="white-b" v-if="landDetailInfos.landBorder">土地四至：
			                        <em class="font12">{{ landDetailInfos.landBorder }}</em>
			                      </div>
			                      <div class="white-b" v-if="landDetailInfos.parcelSN">地块编号：
			                        <em class="font12">{{ landDetailInfos.parcelSN }}</em>
			                      </div>
			                      <div class="white-b" v-if="landDetailInfos.parcelConforming">用地性质：
			                        <em class="font12">{{ landDetailInfos.parcelConforming }}</em>
			                      </div>
			                      <div class="white-b" v-if="landDetailInfos.remiseWay">出让方式：
			                        <em class="font12">{{ landDetailInfos.remiseWay }}</em>
			                      </div>
			                      <div class="white-b" v-if="landDetailInfos.parcelArea">总用地面积：
			                        <em class="font12">{{ landDetailInfos.parcelArea }}</em>&nbsp;㎡</div>
			                      <div class="white-b" v-if="landDetailInfos.collectingArea">代征面积：
			                        <em class="font12">{{ landDetailInfos.collectingArea }}</em>&nbsp;㎡</div>
			                      <div class="white-b" v-if="landDetailInfos.buildArea">建设用地面积：
			                        <em class="font12">{{ landDetailInfos.buildArea }}</em>&nbsp;㎡</div>
			                      <div class="white-b" v-if="landDetailInfos.planningArea">规划建筑面积：
			                        <em class="font12">{{ landDetailInfos.planningArea }}</em>&nbsp;㎡</div>
			                      <div class="white-b" v-if="landDetailInfos.planningAreaOfHouse">住宅类用地规划建面：
			                        <em class="font12">{{ landDetailInfos.planningAreaOfHouse }}</em>&nbsp;㎡</div>
			                      <div class="white-b" v-if="landDetailInfos.planningAreaOfBusiness">商品住宅部分规划建面：
			                        <em class="font12">{{ landDetailInfos.planningAreaOfBusiness }}</em>&nbsp;㎡</div>
			                      <div class="white-b" v-if="landDetailInfos.volumetricRate">容积率：
			                        <em class="font12">{{ landDetailInfos.volumetricRate }}</em></div>
			                      <div class="white-b" v-if="landDetailInfos.developLevel">开发程度：
			                        <em class="font12">{{ landDetailInfos.developLevel }}</em></div>
			                      <div class="white-b" v-if="landDetailInfos.buildingDensity">建筑密度：
			                        <em class="font12">{{ landDetailInfos.buildingDensity }}</em></div>
			                      <div class="white-b" v-if="landDetailInfos.greeningRate">绿化率：
			                        <em class="font12">{{ landDetailInfos.greeningRate }}</em></div>
			                      <div class="white-b" v-if="landDetailInfos.commercialRate">商业比例：
			                        <em class="font12">{{ landDetailInfos.commercialRate }}</em></div>
			                      <div class="white-b" v-if="landDetailInfos.heightLimited">限制高度：
			                        <em class="font12">{{ landDetailInfos.heightLimited }}</em></div>
			                      <div class="white-b" v-if="landDetailInfos.serviceLife">出让年限：
			                        <em class="font12">{{ landDetailInfos.serviceLife }}</em></div>
			                      <div class="white-b" v-if="landDetailInfos.securityHousingType">保障房类型：
			                        <em class="font12">{{ landDetailInfos.securityHousingType }}</em></div>
		                          <div class="white-b" v-if="landDetailInfos.securityHousingArea">成交保障房面积：
			                        <em class="font12">{{ landDetailInfos.securityHousingArea }}</em>&nbsp;㎡</div>
		                          <div class="white-b" v-if="landDetailInfos.initialSecurityHousingArea">推出保障房面积：
			                        <em class="font12">{{ landDetailInfos.initialSecurityHousingArea }}</em>&nbsp;㎡</div>
		                          <div class="white-b" v-if="landDetailInfos.securityHousing">配建保障房情况：
			                        <em class="font12">{{ landDetailInfos.securityHousing }}</em></div>
			                      <div class="white-b" v-if="landDetailInfos.selfHousingTuiChuArea">推出自住房面积：
			                        <em class="font12">{{ landDetailInfos.selfHousingTuiChuArea }}</em>&nbsp;㎡</div>
			                      <div class="white-b" v-if="landDetailInfos.selfHousingChengJiaoArea">成交自住房面积：
			                        <em class="font12">{{ landDetailInfos.selfHousingChengJiaoArea }}</em>&nbsp;㎡</div>
			                      <div class="white-b" v-if="landDetailInfos.selfHousingDescription">配建自住房情况：
			                        <em class="font12">{{ landDetailInfos.selfHousingDescription }}</em></div>
			                      <div class="white-b" v-if="landDetailInfos.detrusionPlicy">推出特殊政策：
			                        <em class="font12">{{ landDetailInfos.detrusionPlicy }}</em></div>
			                      <div class="white-b" v-if="landDetailInfos.dealPolicy">成交特殊政策：
			                        <em class="font12">{{ landDetailInfos.dealPolicy }}</em></div>
			                    </div>
			                  </li>
			                </ul>
			              </div>
			            </div>
			            <!-- 基本信息 end-->
			            <!-- 交易信息 begin-->
		              <div class="panel sf_panel">
		              	<div class="panel-head clearfix">
		                <h3 class="panel-head-title">
		                  <span>交易信息</span>
		                </h3>
		              </div>
		              <div class="panel-body">
		              	<ul class="bk-list ld-list no-margin">
		                  <li>
		                    <div class="buld_infor clearfix">
		                      <div class="white-b" v-if="landDetailInfos.announcementDate">公告时间：
		                        <em class="font12">{{ landDetailInfos.announcementDate }}</em>
		                      </div>
		                      <div class="white-b" v-if="landDetailInfos.startDate">起始时间：
		                        <em class="font12">{{ landDetailInfos.startDate }}</em>
		                      </div>
		                      <div class="white-b" v-if="landDetailInfos.endDate">截止时间：
		                        <em class="font12">{{ landDetailInfos.endDate }}</em>
		                      </div>
		                      <div class="comp white-b" v-if="landDetailInfos.completionDate">成交时间：
		                        <em class="font12">{{ landDetailInfos.completionDate }}</em>
		                      </div>
		                      <div class="comp white-b" v-if="landDetailInfos.dealStatus">交易状况：
		                        <em class="font12">{{ landDetailInfos.dealStatus }}</em>
		                      </div>
		                      <div class="comp white-b" v-if="landDetailInfos.initialPrice">起始价：
		                        <em class="font12">{{ landDetailInfos.initialPrice }}</em>&nbsp;万元
		                      </div>
		                      <div class="comp white-b" v-if="landDetailInfos.closingCost">成交价：
		                        <em class="font12">{{ landDetailInfos.closingCost }}</em>&nbsp;万元
		                      </div>
		                      <div class="comp white-b" v-if="landDetailInfos.avgPremiumRate">溢价率：
		                        <em class="font12">{{ landDetailInfos.avgPremiumRate }}%</em>
		                      </div>
		                      <div class="comp white-b" v-if="landDetailInfos.transferee">受让单位：
		                        <em class="font12">{{ landDetailInfos.transferee }}</em>
		                      </div>
		                      <div class="white-b" v-if="landDetailInfos.initialUnitPrice">推出土地单价：
	                            <em class="font12">{{ landDetailInfos.initialUnitPrice }}</em>&nbsp;元/㎡</div>
		                      <div class="white-b" v-if="landDetailInfos.closingUnitPrice">成交土地单价：
	                            <em class="font12">{{ landDetailInfos.closingUnitPrice }}</em>&nbsp;元/㎡</div>
		                      <div class="white-b" v-if="landDetailInfos.initialmuPrice">推出每亩价：
	                            <em class="font12">{{ landDetailInfos.initialmuPrice }}</em>&nbsp;万元/亩</div>
		                      <div class="white-b" v-if="landDetailInfos.closingmuPrice">成交每亩价：
	                            <em class="font12">{{ landDetailInfos.closingmuPrice }}</em>&nbsp;万元/亩</div>
	                          <div class="white-b" v-if="landDetailInfos.initialFloorPrice">推出楼面价：
	                            <em class="font12">{{ landDetailInfos.initialFloorPrice }}</em>&nbsp;元/㎡</div>
	                          <div class="white-b" v-if="landDetailInfos.closingFloorPrice">成交楼面价：
	                            <em class="font12">{{ landDetailInfos.closingFloorPrice }}</em>&nbsp;元/㎡</div>
	                          <div class="white-b" v-if="landDetailInfos.performanceBond">保证金：
	                            <em class="font12">{{ landDetailInfos.performanceBond }}</em>&nbsp;万元</div>
	                          <div class="white-b" v-if="landDetailInfos.bidIncrements">加价幅度：
	                            <em class="font12">{{ landDetailInfos.bidIncrements }}</em>&nbsp;万元</div>
	                          <div class="white-b" v-if="landDetailInfos.consultTelephone">咨询电话：
	                            <em class="font12">{{ landDetailInfos.consultTelephone }}</em></div>
	                          <div class="white-b" v-if="landDetailInfos.announcementNo">公告编号：
	                            <em class="font12">{{ landDetailInfos.announcementNo }}</em></div>
	                          <div class="white-b" v-if="landDetailInfos.deptAddress">出让单位：
	                            <em class="font12">{{ landDetailInfos.deptAddress }}</em></div>
	                          <div class="white-b" v-if="landDetailInfos.parcelAffiche != null && landDetailInfos.parcelAffiche != ''">
	                          	土地公告：
	                            <em class="font12">
	                            	<a href="javascript:void(0)" @click="downloadFile(landDetailInfos.parcelAffiche, landDetailInfos.parcelSN)">下载公告</a>
                            	</em>
	                          </div>
	                          <div class="white-b" v-if="landDetailInfos.parcelMemo">备注：
	                            <em class="font12">{{ landDetailInfos.parcelMemo }}</em></div>
		                    </div>
		                  </li>
		                </ul>
		              </div>
		              </div>
		              <!-- 交易信息 begin-->
		            </div>
	            </div>
	            <!--弹出地块列表详情 end-->
	            <!--弹出住宅列表详情 start-->
	            <div class="main-data-cnt" style="" v-show="residenceDetailShow">
	            	<div class="h-tit clearfix h-tit-b">
	            		<div class="clearfix">
	            			<h2 class="tit-text fl">住宅详情</h2>
		                <a href="javascript:;" class="btn-back fr cornerbg mt10">
		                  <span class="icon-back" @click="countInfoDetailSwitch('countInfo')"></span>
		                </a>
	            		</div>
	                <h2 class="clearfix tit-text mt05 tit-text1" :title="residenceDetailInfos.sschemename">{{ residenceDetailInfos.sschemename }}
	                <span class="sf-lable-green fr for_sale" v-if="residenceDetailInfos.sstatusid==1">在售</span>
	                <span class="sf-lable-green fr not_for_sale" v-if="residenceDetailInfos.sstatusid==0">未售</span>
	                <span class="sf-lable-green fr sale_out" v-if="residenceDetailInfos.sstatusid==2">售罄</span>
	                <!-- <span class="sf-lable-green fr" v-if="residenceDetailInfos.sstatusid==3">在租</span>
	                <span class="sf-lable-green fr" v-if="residenceDetailInfos.sstatusid==4">租售</span> -->

	                <span class="sf-lable-green fr for_sale" v-if="residenceDetailInfos.communityGrade">{{ residenceDetailInfos.communityGrade }}</span>
	                </h2>
	              </div>
		            <div class="main-data-ptnbox nice-scroll main-data-ptnbox-b">
		            	<!-- 基本信息 begin-->
			            <div class="panel sf_panel">
			              	<!-- <div class="panel-head clearfix">
			                <h3 class="panel-head-title">
			                  <span>基本信息</span>
			                </h3>
			              </div> -->
			              <div class="panel-body">
			              	<ul class="bk-list ld-list no-margin">
			                  <li>
			                  	<!-- <div class="buld-tit clearfix">
	                          <span class="fl text-nowrap b-tit tw01">土地名称名称名称土地名称名称名称</span>
	                          <span class="sf-lable-green fr">已成交</span>
	                        </div> -->
			                    <div class="buld_infor clearfix">
			                      <div class="white-b" v-if="residenceDetailInfos.sbuildcyc">物业类别：
			                        <em class="font12">{{ residenceDetailInfos.sbuildcyc }}</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.buildcategory">建筑类别：
			                        <em class="font12">{{ residenceDetailInfos.buildcategory }}</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.cityZone">环线位置：
			                        <em class="font12" v-text="residenceDetailInfos.cityZone"></em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.fdimension && residenceDetailInfos.fdimension !=0">容积率：
			                        <em class="font12">{{ residenceDetailInfos.fdimension }}</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.dsaledate">开盘时间：
			                        <em class="font12">{{ residenceDetailInfos.dsaledate }}</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.spropfee">物业费：
			                        <em class="font12">{{ residenceDetailInfos.spropfee }}</em>&nbsp;{{ residenceDetailInfos.spropfeetype }}
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.sdeveloper">开发商：
			                        <em class="font12">{{ residenceDetailInfos.sdeveloper }}</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.saleCard">预售许可证：
			                        <em class="font12" v-text="residenceDetailInfos.saleCard"></em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.ssaleaddress">售楼地址：
			                        <em class="font12">{{ residenceDetailInfos.ssaleaddress }}</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.ssaletelphone">联系电话：
			                        <em class="font12">{{ residenceDetailInfos.ssaletelphone }}</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.address">物业地址：
			                        <em class="font12">{{ residenceDetailInfos.address }}</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.busText">交通状况：
			                        <em class="font12">{{ residenceDetailInfos.busText }}</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.priceAverage">价格：
			                        <em class="font12">{{ residenceDetailInfos.priceAverage }}</em>&nbsp;万元/套
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.fgroundarea">占地面积：
			                        <em class="font12">{{ residenceDetailInfos.fgroundarea }}</em>&nbsp;㎡
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.fpurposearea">建筑面积：
			                        <em class="font12">{{ residenceDetailInfos.fpurposearea }}</em>&nbsp;㎡
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.dstartdate">开工时间：
			                        <em class="font12">{{ residenceDetailInfos.dstartdate }}</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.dfinishdate">竣工时间：
			                        <em class="font12">{{ residenceDetailInfos.dfinishdate }}</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.spropertyaddition">物业管理附加信息：
			                        <em class="font12">{{ residenceDetailInfos.spropertyaddition }}</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.sagent">代理商：
			                        <em class="font12">{{ residenceDetailInfos.sagent }}</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.sinvestor">投资商：
			                        <em class="font12">{{ residenceDetailInfos.sinvestor }}</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.spropertymanage">物业管理公司：
			                        <em class="font12">{{ residenceDetailInfos.spropertymanage }}</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.workSchedule">工程进度：
			                        <em class="font12">{{ residenceDetailInfos.workSchedule }}</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.irightyear">产权年限：
			                        <em class="font12">{{ residenceDetailInfos.irightyear }}</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.stotaldoor">户数：
			                        <em class="font12">{{ residenceDetailInfos.stotaldoor }}</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.scharacter">项目特色：
			                        <em class="font12">{{ residenceDetailInfos.scharacter }}</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.sfixstattus">装修状况：
			                        <em class="font12">{{ residenceDetailInfos.sfixstattus }}</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.business">所属商圈：
			                        <em class="font12" v-text="residenceDetailInfos.business"></em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.fvirescencerate">绿化率：
			                        <em class="font12">{{ residenceDetailInfos.fvirescencerate }}%</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.livinDate">交房时间：
			                        <em class="font12" v-text="residenceDetailInfos.livinDate"></em>
			                      </div>
			                      <div class="white-b" v-if="false">物业公司：
			                        <em class="font12">--</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.ssightdesign">景观设计单位：
			                        <em class="font12">{{ residenceDetailInfos.ssightdesign }}</em>
			                      </div>
			                      <div class="white-b" v-if="residenceDetailInfos.slandscape">建筑及园林设计单位：
			                        <em class="font12">{{ residenceDetailInfos.slandscape }}</em>
			                      </div>
			                    </div>
			                  </li>
			                </ul>
			              </div>
			            </div>
			            <!-- 基本信息 end-->
			            <!-- 交易信息 begin-->
		              <!-- <div class="panel sf_panel panel_gray">
		              	<div class="panel-head clearfix">
		                <h3 class="panel-head-title">
		                  <span>交易信息</span>
		                </h3>
		              </div>
		              <div class="panel-body">
		              	<ul class="bk-list ld-list no-margin">
		                  <li>
		                    <div class="buld_infor clearfix">
		                      <div class="white-b">公告时间：
		                        <em class="font12">2017-12-29</em>
		                      </div>
		                      <div class="white-b">起始时间：
		                        <em class="font12">2017-10-20</em>
		                      </div>
		                      <div class="white-b">截止时间：
		                        <em class="font12">2017-12-29</em>
		                      </div>
		                      <div class="comp white-b">成交时间：
		                        <em class="font12">2017-12-29</em>
		                      </div>
		                    </div>
		                  </li>
		                </ul>
		              </div>
		              </div> -->
		              <!-- 交易信息 begin-->
		            </div>
	            </div>
	            <!--弹出住宅列表详情 end-->
	            <!--弹出商办列表详情-商铺 start-->
	            <div class="main-data-cnt" style="" v-show="businessDetailShow">
	            	<div class="h-tit clearfix h-tit-b">
	            		<div class="clearfix">
	            			<h2 class="tit-text fl">商铺详情</h2>
		                <a href="javascript:;" class="btn-back fr cornerbg mt10">
		                  <span class="icon-back" @click="countInfoDetailSwitch('countInfo')"></span>
		                </a>
	            		</div>
	                <h2 class="clearfix tit-text mt05 tit-text1" :title="businessDetailInfos.sschemename">{{ businessDetailInfos.sschemename }}
	                <span class="sf-lable-green fr for_sale" v-if="businessDetailInfos.sstatusid=='在售'">在售</span>
	                <span class="sf-lable-green fr not_for_sale" v-if="businessDetailInfos.sstatusid=='待售'">未售</span>
	                <span class="sf-lable-green fr sale_out" v-if="businessDetailInfos.sstatusid=='售完'">售罄</span>
	                <!-- <span class="sf-lable-green fr" v-if="businessDetailInfos.sstatusid==3">在租</span>
	                <span class="sf-lable-green fr" v-if="businessDetailInfos.sstatusid==4">租售</span> -->

	                <span class="sf-lable-green fr for_sale" v-if="businessDetailInfos.communityGrade">{{ businessDetailInfos.communityGrade }}</span>
	                </h2>
	              </div>
		            <div class="main-data-ptnbox nice-scroll main-data-ptnbox-b">
		            	<!-- 基本信息 begin-->
			            <div class="panel sf_panel">
			              	<!-- <div class="panel-head clearfix">
			                <h3 class="panel-head-title">
			                  <span>基本信息</span>
			                </h3>
			              </div> -->
			              <div class="panel-body">
			              	<ul class="bk-list ld-list no-margin">
			                  <li>
			                  	<!-- <div class="buld-tit clearfix">
	                          <span class="fl text-nowrap b-tit tw01">土地名称名称名称土地名称名称名称</span>
	                          <span class="sf-lable-green fr">已成交</span>
	                        </div> -->
			                    <div class="buld_infor clearfix">
			                      <div class="white-b" v-if="businessDetailInfos.ShopTypeName">商铺类型：
			                        <em class="font12">{{ businessDetailInfos.ShopTypeName }}</em>
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.sfixstattus">装修状况：
			                        <em class="font12">{{ businessDetailInfos.sfixstattus }}</em>
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.fdimension && businessDetailInfos.fdimension != 0">容积率：
			                        <em class="font12">{{ businessDetailInfos.fdimension }}</em>
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.shopUsage">使用率：
			                        <em class="font12">{{ businessDetailInfos.shopUsage }}</em>
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.irightyear">产权年限：
			                        <em class="font12">{{ businessDetailInfos.irightyear }}</em>
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.fgroundarea">占地面积：
			                        <em class="font12">{{ businessDetailInfos.fgroundarea }}</em>&nbsp;㎡
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.shopStandardArea">标准层面积：
			                        <em class="font12">{{ businessDetailInfos.shopStandardArea }}</em>&nbsp;㎡
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.shopBusinessArea">商业面积：
			                        <em class="font12">{{ businessDetailInfos.shopBusinessArea }}</em>&nbsp;㎡
			                      </div>
			                      <div class="white-b" v-if="false">单套面积：
			                        <em class="font12">--</em>&nbsp;㎡
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.shopPartment">楼栋状况：
			                        <em class="font12">{{ businessDetailInfos.shopPartment }}</em>
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.dsaledate">开盘时间：
			                        <em class="font12">{{ businessDetailInfos.dsaledate }}</em>
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.spropfee">物业管理费：
			                        <em class="font12">{{ businessDetailInfos.spropfee }}</em>&nbsp;{{ businessDetailInfos.spropfeetype }}
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.ssaleaddress">售楼地址：
			                        <em class="font12">{{ businessDetailInfos.ssaleaddress }}</em>
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.address">物业地址：
			                        <em class="font12">{{ businessDetailInfos.address }}</em>
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.priceAverage">租售价格：
			                        <em class="font12">{{ businessDetailInfos.priceAverage }}</em>&nbsp;元/㎡
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.spropertyaddition">物业管理费描述：
			                        <em class="font12">{{ businessDetailInfos.spropertyaddition }}</em>
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.shopOnSaleType">出售类型：
			                        <em class="font12">{{ businessDetailInfos.shopOnSaleType }}</em>
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.shopGoalsoperastion">目标业态：
			                        <em class="font12">{{ businessDetailInfos.shopGoalsoperastion }}</em>
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.sdeveloper">开发商：
			                        <em class="font12">{{ businessDetailInfos.sdeveloper }}</em>
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.sinvestor">投资商：
			                        <em class="font12">{{ businessDetailInfos.sinvestor }}</em>
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.spropertymanage">物业管理公司：
			                        <em class="font12">{{ businessDetailInfos.spropertymanage }}</em>
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.shopPriceType">租售状态：
			                        <em class="font12">{{ businessDetailInfos.shopPriceType }}</em>
			                      </div>
			                      <!-- <div class="white-b" v-if="businessDetailInfos.sstatusid==1">租售状态：
			                        <em class="font12">在售</em>
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.sstatusid==2">租售状态：
			                        <em class="font12">售罄</em>
			                      </div> -->
			                      <div class="white-b" v-if="businessDetailInfos.scharacter">项目特色：
			                        <em class="font12">{{ businessDetailInfos.scharacter }}</em>
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.fvirescencerate">绿化率：
			                        <em class="font12">{{ businessDetailInfos.fvirescencerate }}%</em>
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.shopROI">投资回报率：
			                        <em class="font12">{{ businessDetailInfos.shopROI }}</em>
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.shopUsageYear">使用年限：
			                        <em class="font12">{{ businessDetailInfos.shopUsageYear }}</em>
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.fpurposearea">建筑面积：
			                        <em class="font12">{{ businessDetailInfos.fpurposearea }}</em>&nbsp;㎡
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.shopBusinessArea">单层商铺面积：
			                        <em class="font12">{{ businessDetailInfos.shopBusinessArea }}</em>&nbsp;㎡
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.shopOfficeArea">办公面积：
			                        <em class="font12">{{ businessDetailInfos.shopOfficeArea }}</em>&nbsp;㎡
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.shopOpenArea">开间面积：
			                        <em class="font12">{{ businessDetailInfos.shopOpenArea }}</em>&nbsp;㎡
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.stotaldoor && businessDetailInfos.stotaldoor != 0">商铺总套数：
			                        <em class="font12">{{ businessDetailInfos.stotaldoor }}</em>
			                      </div>
			                      <div class="white-b" v-if="businessDetailInfos.livinDate">入住时间：
			                        <em class="font12">{{ businessDetailInfos.livinDate }}</em>
			                      </div>
			                    </div>
			                  </li>
			                </ul>
			              </div>
			            </div>
			            <!-- 基本信息 end-->
			            <!-- 交易信息 begin-->
		              <!-- <div class="panel sf_panel panel_gray">
		              	<div class="panel-head clearfix">
		                <h3 class="panel-head-title">
		                  <span>交易信息</span>
		                </h3>
		              </div>
		              <div class="panel-body">
		              	<ul class="bk-list ld-list no-margin">
		                  <li>
		                    <div class="buld_infor clearfix">
		                      <div class="white-b">公告时间：
		                        <em class="font12">2017-12-29</em>
		                      </div>
		                      <div class="white-b">起始时间：
		                        <em class="font12">2017-10-20</em>
		                      </div>
		                      <div class="white-b">截止时间：
		                        <em class="font12">2017-12-29</em>
		                      </div>
		                      <div class="comp white-b">成交时间：
		                        <em class="font12">2017-12-29</em>
		                      </div>
		                    </div>
		                  </li>
		                </ul>
		              </div>
		              </div> -->
		              <!-- 交易信息 begin-->
		            </div>
	            </div>
	            <!--弹出商办列表详情-商铺 end-->
	            <!--弹出商办列表详情-写字楼 start-->
	            <div class="main-data-cnt" style="" v-show="officeDetailShow">
	            	<div class="h-tit clearfix h-tit-b">
	            		<div class="clearfix">
	            			<h2 class="tit-text fl">写字楼详情</h2>
		                <a href="javascript:;" class="btn-back fr cornerbg mt10">
		                  <span class="icon-back" @click="countInfoDetailSwitch('countInfo')"></span>
		                </a>
	            		</div>
	                <h2 class="clearfix tit-text mt05 tit-text1" :title="officeDetailInfos.sschemename">{{ officeDetailInfos.sschemename }}
	                <span class="sf-lable-green fr for_sale" v-if="officeDetailInfos.sstatusid==1">在售</span>
	                <span class="sf-lable-green fr not_for_sale" v-if="officeDetailInfos.sstatusid==0">未售</span>
	                <span class="sf-lable-green fr sale_out" v-if="officeDetailInfos.sstatusid==2">售罄</span>
	                <!-- <span class="sf-lable-green fr" v-if="officeDetailInfos.sstatusid==3">在租</span>
	                <span class="sf-lable-green fr" v-if="officeDetailInfos.sstatusid==4">租售</span> -->

	                <span class="sf-lable-green fr for_sale" v-if="officeDetailInfos.communityGrade">{{ officeDetailInfos.communityGrade }}</span>
	                </h2>
	              </div>
		            <div class="main-data-ptnbox nice-scroll main-data-ptnbox-b">
		            	<!-- 基本信息 begin-->
			            <div class="panel sf_panel">
			              	<!-- <div class="panel-head clearfix">
			                <h3 class="panel-head-title">
			                  <span>基本信息</span>
			                </h3>
			              </div> -->
			              <div class="panel-body">
			              	<ul class="bk-list ld-list no-margin">
			                  <li>
			                  	<!-- <div class="buld-tit clearfix">
	                          <span class="fl text-nowrap b-tit tw01">土地名称名称名称土地名称名称名称</span>
	                          <span class="sf-lable-green fr">已成交</span>
	                        </div> -->
			                    <div class="buld_infor clearfix">
			                      <div class="white-b" v-if="officeDetailInfos.officeTypeName">写字楼类型：
			                        <em class="font12" v-text="officeDetailInfos.officeTypeName"></em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.cityZone">环线位置：
			                        <em class="font12" v-text="officeDetailInfos.cityZone"></em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.officePriceType">租售状态：
			                        <em class="font12" v-text="officeDetailInfos.officePriceType"></em>
			                      </div>
			                      <!-- <div class="white-b" v-if="officeDetailInfos.sstatusid==1">租售状态：
			                        <em class="font12">在售</em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.sstatusid==2">租售状态：
			                        <em class="font12">售罄</em>
			                      </div> -->
			                      <div class="white-b" v-if="officeDetailInfos.sfixstattus">装修状况：
			                        <em class="font12">{{ officeDetailInfos.sfixstattus }}</em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.fdimension">容积率：
			                        <em class="font12">{{ officeDetailInfos.fdimension }}</em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.officeUsage">使用率：
			                        <em class="font12">{{ officeDetailInfos.officeUsage }}</em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.fgroundarea">占地面积：
			                        <em class="font12">{{ officeDetailInfos.fgroundarea }}</em>&nbsp;㎡
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.officeStandardArea">标准层面积：
			                        <em class="font12">{{ officeDetailInfos.officeStandardArea }}</em>&nbsp;㎡
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.officeBusinessArea">商业面积：
			                        <em class="font12">{{ officeDetailInfos.officeBusinessArea }}</em>&nbsp;㎡
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.officePartment">楼栋状况：
			                        <em class="font12">{{ officeDetailInfos.officePartment }}</em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.dsaledate">开盘时间：
			                        <em class="font12">{{ officeDetailInfos.dsaledate }}</em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.spropfee">物业管理费：
			                        <em class="font12">{{ officeDetailInfos.spropfee }}</em>&nbsp;{{ officeDetailInfos.spropfeetype }}
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.ssaleaddress">售楼地址：
			                        <em class="font12">{{ officeDetailInfos.ssaleaddress }}</em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.address">物业地址：
			                        <em class="font12">{{ officeDetailInfos.address }}</em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.priceAverage">租售价格：
			                        <em class="font12">{{ officeDetailInfos.priceAverage }}</em>&nbsp;元/㎡
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.saleCard">预售许可证：
			                        <em class="font12" v-text="officeDetailInfos.saleCard"></em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.spropertyaddition">物业管理费描述：
			                        <em class="font12">{{ officeDetailInfos.spropertyaddition }}</em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.officeOnSaleType">出售类型：
			                        <em class="font12">{{ officeDetailInfos.officeOnSaleType }}</em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.officeOnRentType">出租类型：
			                        <em class="font12">{{ officeDetailInfos.officeOnRentType }}</em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.sdeveloper">开发商：
			                        <em class="font12">{{ officeDetailInfos.sdeveloper }}</em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.spropertymanage">物业管理公司：
			                        <em class="font12">{{ officeDetailInfos.spropertymanage }}</em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.officeLevel">写字楼级别：
			                        <em class="font12">{{ officeDetailInfos.officeLevel }}</em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.business">所属商圈：
			                        <em class="font12" v-text="officeDetailInfos.business"></em>
			                      </div>
			                      <div class="white-b" v-if="false">所属分期：
			                        <em class="font12">--</em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.scharacter">项目特色：
			                        <em class="font12">{{ officeDetailInfos.scharacter }}</em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.fvirescencerate">绿化率：
			                        <em class="font12">{{ officeDetailInfos.fvirescencerate }}%</em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.irightyear">产权年限：
			                        <em class="font12">{{ officeDetailInfos.irightyear }}</em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.fpurposearea">建筑面积：
			                        <em class="font12">{{ officeDetailInfos.fpurposearea }}</em>&nbsp;㎡
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.officeOpenArea">开间面积：
			                        <em class="font12">{{ officeDetailInfos.officeOpenArea }}</em>&nbsp;㎡
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.officeOfficeArea">办公面积：
			                        <em class="font12">{{ officeDetailInfos.officeOfficeArea }}</em>&nbsp;㎡
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.officePartment">当前楼栋数：
			                        <em class="font12">{{ officeDetailInfos.officePartment }}</em>
			                      </div>
			                      <div class="white-b" v-if="officeDetailInfos.livinDate">入住时间：
			                        <em class="font12">{{ officeDetailInfos.livinDate }}</em>
			                      </div>
			                    </div>
			                  </li>
			                </ul>
			              </div>
			            </div>
			            <!-- 基本信息 end-->
			            <!-- 交易信息 begin-->
		              <!-- <div class="panel sf_panel panel_gray">
		              	<div class="panel-head clearfix">
		                <h3 class="panel-head-title">
		                  <span>交易信息</span>
		                </h3>
		              </div>
		              <div class="panel-body">
		              	<ul class="bk-list ld-list no-margin">
		                  <li>
		                    <div class="buld_infor clearfix">
		                      <div class="white-b">公告时间：
		                        <em class="font12">2017-12-29</em>
		                      </div>
		                      <div class="white-b">起始时间：
		                        <em class="font12">2017-10-20</em>
		                      </div>
		                      <div class="white-b">截止时间：
		                        <em class="font12">2017-12-29</em>
		                      </div>
		                      <div class="comp white-b">成交时间：
		                        <em class="font12">2017-12-29</em>
		                      </div>
		                    </div>
		                  </li>
		                </ul>
		              </div>
		              </div> -->
		              <!-- 交易信息 begin-->
		            </div>
	            </div>
	            <!--弹出商办列表详情 end-->
            </div>
          </div>
          <!-- 右侧数据展示 end-->
          <!-- 地图内容 begin-->
          <div class="map-main">
            <div class="map_compare par_1">
              <!-- 三个地图展示class="par_3",两个地图展示class="par_2",一个地图展示class="par_1" -->
              <!-- 地图1 begin-->
              <div class="fl">
                <span>现状价值</span>
                <!-- 信息弹层 begin-->
                <!-- 信息弹层 end-->
                <!-- 放大缩小全屏 begin-->
                <div class="ancillary-tools">
                  <ul>
                    <li @click='gMapDown'>
                      <a href="javascript:;" title="下载">
                        <em class="iconbox icon_download"></em>
                      </a>
                    </li>
                   <!--  <li>
                      <a href="javascript:;" title="全屏">
                        <em class="iconbox icon_fullscreen"></em>
                      </a>
                    </li> -->
                    <li @click='zoomIn'>
                      <a href="javascript:;" data-citymap-tool="zoom-in" title="放大一级视图">
                        <em class="iconbox icon_enlarge"></em>
                      </a>
                    </li>
                    <li @click='zoomOut'>
                      <a href="javascript:;" data-citymap-tool="zoom-out" title="缩小一级视图">
                        <em class=" iconbox icon_shrink"></em>
                      </a>
                    </li>
                  </ul>
                </div>
                <!-- 放大缩小全屏 end-->
                <!-- 地图图例  begin
                <div class="legend-container">
                  <a href="javascript:;" class="btn btn-default btn-sm" style="display:">
                    <em class="iconbox icon_legend"></em>
                  </a>
                  <div class="legendbox">
                    <div class="tb_tit clearfix">
                      <span class="font13 graya">分值</span>
                      <i class="wenh"></i>
                    </div>
                    <div class="tb_cnt">
                      <p class="font12">
                        <i style="background-color:#ce6656"></i>76~100</p>
                      <p class="font12">
                        <i style="background-color:#ce6656"></i>76~100</p>
                      <p class="font12">
                        <i style="background-color:#ce6656"></i>76~100</p>
                    </div>
                  </div>
                </div>
                end-->
                <div class="obj-map" id="map" v-citymap-loading.standard="myLoading"></div>
              </div>
              <!-- 地图1 end-->

            </div>

          </div>
          <!-- 地图内容 end-->
        </div>
</template>

<script>
var mapIconLandTips;
//地图一览
import api from "./../api.js";
import datafilter from "./map-datafilter.vue";
import map from "./../map.js";
//通用全局参数
var mapParam = {
  boardId: boardId,
  startTime: getBeforeYear(3),
  endTime: getNow(),
  instalmentId: "",
  ownerId:'standard'
};
//POI全局参数
var mapPoiParam = {
  cityName: cityName,
  demissionType: "板块",
  iTagIDList: ["D692A919-188D-4008-AE5B-8B51DD21DF47:7397"],
  index: 0,
  ownerID: "standard",
  sObjID: [boardId],
  sTypeIDList: ["BEA694FF-8EE5-4C0D-8808-DF8232ACC09B"],
  cityId:cityId
};
//房产全局参数
var mapHouseParam = {
  analysisType: "market",
  area: "-",
  boardId: boardId,
  cityId: cityId,
  endDate: getNow(),
  groupType: "project",
  housePattern: "-",
  houseType: "",
  propertyType: "",
  propertyType2: "",
  startDate: getBeforeYear(1),
  totalPrice: "-",
  unitPrice: "-"
};
//滚动条
var nice_scroll_classobj = {
  styler: "fb",
  cursorcolor: "#bdbec7",
  cursorwidth: "8",
  cursorborderradius: "10px",
  background: "#fff",
  spacebarenabled: false,
  autohidemode: "leave",
  cursorborder: "0",
  zindex: "1000"
};

//获取当前时间
function getNow() {
  var d = new Date();
  return d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
}
//获取n年前的时间
function getBeforeYear(n) {
  var d = new Date();
  return d.getFullYear() - n + "-" + (d.getMonth() + 1) + "-" + d.getDate();
}

export default {
  name: "content-map",
  components: {
    datafilter
  },
  data() {
    return {
      maps: [],
      pageNum: 1,
      newHouseTotal: 0,
      secHouseTotal: 0,
      shopTotal: 0,
      officeTotal: 0,
      myLoading : true,
      loadhouse: false,
      pointUid: "",
      hotmapUid:"",
      situatPlann:'',
      situatPlanns:[],
      element:'',
      elements:[],
      elementsActuality:[],
      elementsPlan:[],
      //'POI'数量
      poiCount:0,
      //'统计信息'的'POI'明细信息
      getPoiDetailsInfo: {
        //展示数据
    	poiDetailsInfo: [],
        //现状数据
    	poiActualityDetails: [],
        //规划数据
    	poiPlanDetails: []
      },
      //'统计信息'的'房产'明细信息
      //新房住宅
      getNewHouseResidenceDetails: {
        newHouseResidenceDetails: []
      },
      //二手房住宅
      getSecondHouseResidenceDetails: {
        secondHouseResidenceDetails: []
      },
      //新房商铺
      getNewHouseShopDetails: {
        newHouseShopDetails: []
      },
      //二手房商铺
      getSecondHouseShopDetails: {
        secondHouseShopDetails: []
      },
      //新房写字楼
      getNewHouseOfficeDetails: {
        newHouseOfficeDetails: []
      },
      //二手房写字楼
      getSecondHouseOfficeDetails: {
        secondHouseOfficeDetails: []
      },
      //'统计信息'的'土地'明细信息
      getLandItemDetail: {
        landItemDetail: []
      },
      //数据筛选参数
      params: {},
   	  //'统计信息'各标签是否选中的高亮效果
      POILi: true,
      landLi: false,
      residenceLi: false,
      businessLi: false,
      //'统计信息'各标签下的内容显示隐藏
      POIShow: true,
      landShow: false,
      residenceShow: false,
      businessShow: false,
      //'统计信息'和点击名称弹出的'详情'显示隐藏
      countInfoShow: true,
      landDetailShow: false,
      residenceDetailShow: false,
      businessDetailShow: false,
      officeDetailShow: false,
      //详情信息数据
      landDetailInfos: {},
      residenceDetailInfos: {},
      businessDetailInfos: {},
      officeDetailInfos: {}
    };
  },
  methods: {
	//住宅加载更多
	  initMoreHouse(target) {
	      let self = this;
	      //console.log("住宅加载更多");
	      //console.log(self.params);
	      self.loadhouse = true;
	      self.pageNum = self.pageNum + 1;
	      if (target == "house") {
	        //房产 boardDetailsHouseDtoList
	        let _houseParam = {
	          data: {
	            cityId: cityId,
	            cityName: cityName,
	            boardId: boardId,
	            propertyType2: "住宅",
	            pageNum: self.pageNum,
	            pageSize: 5
	          },
	          callback: function(response) {
	            self.loadhouse = false;
	            if (response != 50001) {
	              response.forEach(item => {
	                if (item.houseType == "新房") {
	                  if (item.propertyType == "住宅") {
	                	if(item.boardDetailsHouseDtoList){
		                    self.$set(
		                      self.getNewHouseResidenceDetails,
		                      "newHouseResidenceDetails",
		                      self.getNewHouseResidenceDetails.newHouseResidenceDetails.concat(
		                        item.boardDetailsHouseDtoList
		                      )
		                    );
		                    self.$nextTick(() => {
		              	      $(".nice-scroll").niceScroll(nice_scroll_classobj);
		              	      $(".nice-scroll")
		              	        .getNiceScroll()
		              	        .resize();
		                    })
	                	}
	                    if (self.params.project.indexOf("newhouse") > -1) {
	                      //新房住宅
	                      if(item.boardDetailsHouseDtoList){
	                     	 self.getNewhouseIcon(item.boardDetailsHouseDtoList);
	                      }
	                    }
	                  }
	                } else if (item.houseType == "二手房") {
	                  if (item.propertyType == "住宅") {
	                	if(item.boardDetailsHouseDtoList){
		                    self.$set(
		                      self.getSecondHouseResidenceDetails,
		                      "secondHouseResidenceDetails",
		                      self.getSecondHouseResidenceDetails.secondHouseResidenceDetails.concat(
		                        item.boardDetailsHouseDtoList
		                      )
		                    );
		                    self.$nextTick(() => {
		              	      $(".nice-scroll").niceScroll(nice_scroll_classobj);
		              	      $(".nice-scroll")
		              	        .getNiceScroll()
		              	        .resize();
		                    })
	                	}
	                    if (self.params.project.indexOf("esfhouse") > -1) {
	                      //二手房住宅
	                      if(item.boardDetailsHouseDtoList){
	                      	self.getEsfhouseIcon(item.boardDetailsHouseDtoList);
	                      }
	                    }
	                  }
	                }
	              });
	            }
	          }
	        };
	        api.requestData.map.getNewHouseResidenceDetails(_houseParam);
	      } else if (target == "shop") {
	        //房产 boardDetailsHouseDtoList
	        let _houseParam = {
	          data: {
	            cityId: cityId,
	            cityName: cityName,
	            boardId: boardId,
	            propertyType2: "商业",
	            pageNum: self.pageNum,
	            pageSize: 5
	          },
	          callback: function(response) {
	            self.loadhouse = false;
	            if (response != 50001) {
	              response.forEach(item => {
	                if (item.houseType == "新房") {
	                  if (item.propertyType == "商铺") {
	                	if(item.boardDetailsHouseDtoList){
		                    self.$set(
		                      self.getNewHouseShopDetails,
		                      "newHouseShopDetails",
		                      self.getNewHouseShopDetails.newHouseShopDetails.concat(
		                        item.boardDetailsHouseDtoList
		                      )
		                    );
		                    self.$nextTick(() => {
		              	      $(".nice-scroll").niceScroll(nice_scroll_classobj);
		              	      $(".nice-scroll")
		              	        .getNiceScroll()
		              	        .resize();
		                    })
	                	}
	                    if (self.params.project.indexOf("shops") > -1) {
	                      //新房商铺
	                      if(item.boardDetailsHouseDtoList){
	                       self.getShopsIcon(item.boardDetailsHouseDtoList);
	                      }
	                    }
	                  } else if (item.propertyType == "写字楼") {
	                	  if(item.boardDetailsHouseDtoList){
		                    self.$set(
		                      self.getNewHouseOfficeDetails,
		                      "newHouseOfficeDetails",
		                      self.getNewHouseOfficeDetails.newHouseOfficeDetails.concat(
		                        item.boardDetailsHouseDtoList
		                      )
		                    );
		                    self.$nextTick(() => {
		              	      $(".nice-scroll").niceScroll(nice_scroll_classobj);
		              	      $(".nice-scroll")
		              	        .getNiceScroll()
		              	        .resize();
		                    })
	                	  }
	                    if (self.params.project.indexOf("offices") > -1) {
	                      //新房写字楼
	                      if(item.boardDetailsHouseDtoList){
	                        self.getShopsIcon(item.boardDetailsHouseDtoList);
	                      }
	                    }
	                  }
	                } else if (item.houseType == "二手房") {
	                  if (item.propertyType == "商铺") {
	                	if(item.boardDetailsHouseDtoList){
		                    self.$set(
		                      self.getSecondHouseShopDetails,
		                      "secondHouseShopDetails",
		                      self.getSecondHouseShopDetails.secondHouseShopDetails.concat(
		                        item.boardDetailsHouseDtoList
		                      )
		                    );
		                    self.$nextTick(() => {
		              	      $(".nice-scroll").niceScroll(nice_scroll_classobj);
		              	      $(".nice-scroll")
		              	        .getNiceScroll()
		              	        .resize();
		                    })
	                	}
	                    if (self.params.project.indexOf("shops") > -1) {
	                      //二手房商铺
	                      if(item.boardDetailsHouseDtoList){
	                        self.getShopsIcon(item.boardDetailsHouseDtoList);
	                      }
	                    }
	                  } else if (item.propertyType == "写字楼") {
	                	if(item.boardDetailsHouseDtoList){
		                    self.$set(
		                      self.getSecondHouseOfficeDetails,
		                      "secondHouseOfficeDetails",
		                      self.getSecondHouseOfficeDetails.secondHouseOfficeDetails.concat(
		                        item.boardDetailsHouseDtoList
		                      )
		                    );
		                    self.$nextTick(() => {
		              	      $(".nice-scroll").niceScroll(nice_scroll_classobj);
		              	      $(".nice-scroll")
		              	        .getNiceScroll()
		              	        .resize();
		                    })
	                	}
	                    if (self.params.project.indexOf("offices") > -1) {
	                      //二手房写字楼
	                      if(item.boardDetailsHouseDtoList){
	                       self.getShopsIcon(item.boardDetailsHouseDtoList);
	                      }
	                    }
	                  }
	                }
	              });
	            }
	          }
	        };
	        api.requestData.map.getNewHouseResidenceDetails(_houseParam);
	      }
	 },
    classesChange(value) {
		//console.log('value----');
		//console.log(value);
		var self = this;
		if(value=='现状'){
			self.getPoiDetailsInfo.poiDetailsInfo = self.getPoiDetailsInfo.poiActualityDetails;
			self.elements=self.elementsActuality;
          	self.element=self.elementsActuality[0];
		}else if(value=='规划'){
			self.getPoiDetailsInfo.poiDetailsInfo = self.getPoiDetailsInfo.poiPlanDetails;
			self.elements=self.elementsPlan;
          	self.element=self.elementsPlan[0];
		}
		self.$nextTick(() => {
	  	      $(".nice-scroll").niceScroll(nice_scroll_classobj);
	  	      $(".nice-scroll")
	  	        .getNiceScroll()
	  	        .resize();
        })
    },
    poiTypesChange(value) {
		//console.log('value----');
		//console.log(value);
    	var self = this;
    	self.element=value;
    	self.$nextTick(() => {
	  	      $(".nice-scroll").niceScroll(nice_scroll_classobj);
	  	      $(".nice-scroll")
	  	        .getNiceScroll()
	  	        .resize();
      })
    },

    poiMouseover(item) {
            let gMap = map.gMap;
       	    let  id = 'poi_point_' + item.iID;
			let marker = gMap.overlays.image[id];
			if(marker){
	            marker.layer.options.size = [46,54];
	            gMap.removeOverlay('image', id);
	            gMap.addOverlay('image', id, marker);
			}
    },
    poiMouseout(item) {
            let gMap = map.gMap;
       	    let  id = 'poi_point_' + item.iID;
			let marker = gMap.overlays.image[id];
			if(marker){
	            marker.layer.options.size = [31,36];
	            gMap.removeOverlay('image', id);
	            gMap.addOverlay('image', id, marker);
			}
    },
    landMouseover(item) {
            let gMap = map.gMap;
       	    let  id = 'land_point_' + item.parcelId;
			let marker = gMap.overlays.image[id];
			if(marker){
	            marker.layer.options.size = [46,54];
	            gMap.removeOverlay('image', id);
	            gMap.addOverlay('image', id, marker);
			}
    },
    landMouseout(item) {
            let gMap = map.gMap;
       	    let  id = 'land_point_' + item.parcelId;
			let marker = gMap.overlays.image[id];
			if(marker){
	            marker.layer.options.size = [31,36];
	            gMap.removeOverlay('image', id);
	            gMap.addOverlay('image', id, marker);
			}
    },
    houseMouseOver(item,target) {
            let gMap = map.gMap;
       	    let  id = target + item.projectId;
			let marker = gMap.overlays.image[id];
			if(marker){
	            marker.layer.options.size = [46,54];
	            gMap.removeOverlay('image', id);
	            gMap.addOverlay('image', id, marker);
			}
    },
    houseMouseOut(item,target) {
            let gMap = map.gMap;
       	    let  id = target + item.projectId;
			let marker = gMap.overlays.image[id];
			if(marker){
	            marker.layer.options.size = [31,36];
	            gMap.removeOverlay('image', id);
	            gMap.addOverlay('image', id, marker);
			}
    },

	  switchModule(){
		  var self = this;
		  self.$nextTick(() => {
		      $(".nice-scroll").niceScroll(nice_scroll_classobj);
		      $(".nice-scroll")
		        .getNiceScroll()
		        .resize();
	      })
	  },
    showChildMsg(params) {
      var self = this;
      self.myLoading = true;
      if(mapIconLandTips) {
  		$.each(mapIconLandTips,function(i,val) {
  			map.gMap.removeMapTip(val);
  		})
  	  };
      if(params.poiActuality.length == 0 && params.poiPlan.length == 0 && params.project.length == 0) {
        self.myLoading = false;
      }
      self.params = params;
      let paramObj = self.ProcessingParam(params);
      //热力图
      //self.getHotMapData(params.people);
      //点图
      if(map.gMap && map.gMap.overlays) {
        let image = map.gMap.overlays.image;
        if(!$.isEmptyObject(image)) {
          map.gMap.removeOverlay('image');
        }
      }
      if(params.poiActuality.length>0 || params.poiPlan.length>0) {
        self.getPointData(paramObj);
      }

      self.countInfoShow = true;
      self.landDetailShow = false;
      self.residenceDetailShow = false;
      self.businessDetailShow = false;
      self.officeDetailShow = false;

      self.POILi = true;
      self.landLi = false;
      self.residenceLi = false;
      self.businessLi = false;
      self.POIShow = true;
      self.landShow = false;
      self.residenceShow = false;
      self.businessShow = false;

      self.getPoiDetailsInfo.poiDetailsInfo = [];
      self.getPoiDetailsInfo.poiActualityDetails = [];
      self.getPoiDetailsInfo.poiPlanDetails = [];
     /*  self.getNewHouseResidenceDetails.newHouseResidenceDetails = [];
      self.getSecondHouseResidenceDetails.secondHouseResidenceDetails = [];
      self.getNewHouseShopDetails.newHouseShopDetails = [];
      self.getSecondHouseShopDetails.secondHouseShopDetails = [];
      self.getNewHouseOfficeDetails.newHouseOfficeDetails = [];
      self.getSecondHouseOfficeDetails.secondHouseOfficeDetails = []; */
      self.getLandItemDetail.landItemDetail = [];
      self.situatPlanns=[];
      self.situatPlann='';
      self.element='',
      self.elements=[],
      self.elementsActuality=[],
      self.elementsPlan=[],
      self.poiCount=0

      //执行核心方法
      this.indexMethod(params);
    },
    ProcessingParam(params) {
      let iTagIDList = [];
      let sTypeIDList = [];
      let poiActuality = params.poiActuality;
      let poiPlan = params.poiPlan;
      if (poiActuality.length > 0) {
        poiActuality.forEach(val => {
          if (val.checkall == true) {
            sTypeIDList.push(val.typeId);
          } else {
            let typeId = val.typeId;
            val.tags.forEach(val => {
              iTagIDList.push(typeId + ":" + val.tagName);
            });
          }
        });
      }
      if (poiPlan.length > 0) {
        poiPlan.forEach(val => {
          if (val.checkall == true) {
            sTypeIDList.push(val.typeId);
          } else {
            let typeId = val.typeId;
            val.tags.forEach(val => {
              iTagIDList.push(typeId + ":" + val.tagName);
            });
          }
        });
      }
      return { iTagIDList: iTagIDList, sTypeIDList: sTypeIDList };
    },
    getMapData(point,boardName) {
      var self = this;
      let getDataUid = {
        data: {
          cityId: cityId,
          ownerId: "standard",
          boardId: boardId,
          boardName:boardName
        },
        callback: function(result) {
          if (result != 50001) {
            map.draw.mapDataViz(result.dataUid.dataUid, point);
          }
        }
      };
      api.requestData.map.getDataUid(getDataUid);
    },
    getHotMapData(peopleType) {
      var self = this;
       if (self.hotmapUid) {
        map.gMap.removeDataViz(self.hotmapUid);
      }
      let getHotDataUid = {
        data: {
          cityId: cityId,
          peopleType: peopleType,
          boardId: boardId
        },
        callback: function(result) {
          if (result != 50001) {
            let typeObj;
            if(peopleType == 'livePeople') {
              typeObj = [{
                    'value': 'liveCount',
                    'text': '居住人口',
                    isNumber: true
                }];
            }else {
              typeObj = [{
                          'value': 'workCount',
                          'text': '工作人口',
                          isNumber: true
                      }];
            }
           //map.draw.hotmapDataViz(result,typeObj);
          map.changeDataUid(result.dataUid.dataUid);
          let polyObj =  $("#visualizationConfig").GetCityMapControl("mapVisualization").getLayer('hotPoygon');
          polyObj.set({'data':result.datas,'types':typeObj});
           self.hotmapUid = result.dataUid.dataUid;
          }
        }
      };
      api.requestData.map.getPointMap(getHotDataUid);

    },
    getPointData(paramObj) {
      var self = this;
      // if (self.pointUid) {
      //   map.gMap.removeDataViz(self.pointUid);
      // }
      let getPointUid = {
        data: {
          cityId: cityId,
          cityName: cityName,
          demissionType: "板块",
          iTagIDList: paramObj.iTagIDList,
          index: 0,
          ownerID: "standard",
          sObjID: [boardId],
          sTypeIDList: paramObj.sTypeIDList
        },
        callback: function(result) {
          if (result != 50001) {
            // map.draw.pointDataViz(result);
            // self.pointUid = result.dataUidPOIObj.dataUid;
            //console.log(result);
            self.getPoiIcon(result);
          }
          self.myLoading = false;
        }
      };
      api.requestData.map.getPointDataUid(getPointUid);
    },
    //'统计信息'各标签是否选中的高亮效果和各标签下的内容显示隐藏
    countInfoSwitch: function(countType,count) {
      var self = this;
      if(count>0){
	      if (countType == "POI") {
	  	    self.POILi = true;
	        self.landLi = false;
	        self.residenceLi = false;
	        self.businessLi = false;
	    	self.POIShow = true;
	        self.landShow = false;
	        self.residenceShow = false;
	        self.businessShow = false;
	      } else if (countType == "land") {
	        self.POILi = false;
	        self.landLi = true;
	        self.residenceLi = false;
	        self.businessLi = false;
	        self.POIShow = false;
	        self.landShow = true;
	        self.residenceShow = false;
	        self.businessShow = false;
	      } else if (countType == "residence") {
	        self.POILi = false;
	        self.landLi = false;
	        self.residenceLi = true;
	        self.businessLi = false;
	        self.POIShow = false;
	        self.landShow = false;
	        self.residenceShow = true;
	        self.businessShow = false;
	      } else {
	        self.POILi = false;
	        self.landLi = false;
	        self.residenceLi = false;
	        self.businessLi = true;
	        self.POIShow = false;
	        self.landShow = false;
	        self.residenceShow = false;
	        self.businessShow = true;
	      }
      }
      self.$nextTick(() => {
	      $(".nice-scroll").niceScroll(nice_scroll_classobj);
	      $(".nice-scroll")
	        .getNiceScroll()
	        .resize();
      })
    },
    countInfoDetailSwitch: function(
      countInfoDetailType,
      houseType,
      projectId,
      detailInfos
    ) {
      var self = this;
      if (countInfoDetailType == "landDetail") {
        self.landDetailShow = true;
        self.countInfoShow = false;
        self.residenceDetailShow = false;
        self.businessDetailShow = false;
        self.officeDetailShow = false;
        //self.landDetailInfos = detailInfos;

        let getLandItemDetail = {
                data: { landId: detailInfos.parcelId },
                callback: function(data) {
                  self.landDetailInfos = data;
                }
              };
         api.requestData.map.getLandItemDetail(getLandItemDetail);

      } else if (countInfoDetailType == "residenceDetail") {
        self.residenceDetailShow = true;
        self.countInfoShow = false;
        self.landDetailShow = false;
        self.businessDetailShow = false;
        self.officeDetailShow = false;
        //获取住宅项目详情
        self.getSingleProjectDetail("residenceDetail", projectId,houseType,detailInfos);
      } else if (countInfoDetailType == "businessDetail") {
        self.businessDetailShow = true;
        self.countInfoShow = false;
        self.landDetailShow = false;
        self.residenceDetailShow = false;
        self.officeDetailShow = false;
        //获取商铺项目详情
        self.getSingleProjectDetail("businessDetail", projectId,houseType,detailInfos);
      } else if (countInfoDetailType == "officeDetail") {
        self.officeDetailShow = true;
        self.countInfoShow = false;
        self.landDetailShow = false;
        self.residenceDetailShow = false;
        self.businessDetailShow = false;
        //获取写字楼项目详情
        self.getSingleProjectDetail("officeDetail", projectId,houseType,detailInfos);
      } else {
        self.countInfoShow = true;
        self.landDetailShow = false;
        self.residenceDetailShow = false;
        self.businessDetailShow = false;
        self.officeDetailShow = false;
      }
      self.$nextTick(() => {
	      $(".nice-scroll").niceScroll(nice_scroll_classobj);
	      $(".nice-scroll")
	        .getNiceScroll()
	        .resize();
      })
    },
    getSingleProjectDetail: function(detailPropertyType, projectId,houseType,detailInfos) {
      var self = this;
      mapParam.instalmentId = projectId;
      let getSingleProjectDetail = {
        data: mapParam,
        callback: function(singleProjectDetail) {
          if (singleProjectDetail != 50001) {
	          if (detailPropertyType == "residenceDetail") {
	            if(houseType=='secondHouse'){
	            	singleProjectDetail.sstatusid="-1";
	            	singleProjectDetail.communityGrade=detailInfos.communityGrade;
	            }
	        	self.residenceDetailInfos = singleProjectDetail;
	          } else if (detailPropertyType == "businessDetail") {
	        	if(houseType=='secondHouse'){
		           	singleProjectDetail.sstatusid="-1";
		           	singleProjectDetail.communityGrade=detailInfos.communityGrade;
		        }
	            self.businessDetailInfos = singleProjectDetail;
	          } else {
	        	if(houseType=='secondHouse'){
			         singleProjectDetail.sstatusid="-1";
			         singleProjectDetail.communityGrade=detailInfos.communityGrade;
			    }
	        	self.officeDetailInfos = singleProjectDetail;
	          }
          }
          self.$nextTick(() => {
    	      $(".nice-scroll").niceScroll(nice_scroll_classobj);
    	      $(".nice-scroll")
    	        .getNiceScroll()
    	        .resize();
          })
        }
      };
      //获取项目详情
      api.requestData.map.getSingleProjectDetail(getSingleProjectDetail);
    },
    indexMethod: function(paramsData) {
      //vue对象
      var self = this;

      //如果一个都没选，清空所有的点
      if(paramsData.poiActuality.length==0 && paramsData.poiPlan.length==0 && paramsData.project.length==0 ){
    	  let gMap=map.gMap;
    	  gMap.clearOverlays();  //清除所有覆盖物
    	  return ;
      }

      //判断是否有选择的POI现状和规划的条件
      //现状
      if(paramsData.poiActuality.length>0){
    	  self.situatPlanns.push('现状');
    	  self.situatPlann='现状';
    	  //获取POI现状详情数据
    	  //获取'统计信息'的'POI'明细信息并填充数据到页面上----参数(现状)
	      let getPoiActualityDetails = {
	        data: mapPoiParam,
	        callback: function(poiDetailsInfo) {
	       	  if (poiDetailsInfo != 50001) {
	            //console.log(poiDetailsInfo);
	          	self.getPoiDetailsInfo.poiActualityDetails = poiDetailsInfo;
	          	self.getPoiDetailsInfo.poiDetailsInfo = poiDetailsInfo;
	          	for(var i=0;i<poiDetailsInfo.length;i++){
	          		if(self.elementsActuality.indexOf(poiDetailsInfo[i].cname)==-1){
	          			self.elementsActuality.push(poiDetailsInfo[i].cname);
	          			//poi数量
	          			for(var j=0;j<poiDetailsInfo[i].boardDetailsResourceDtoList.length;j++){
	          				self.poiCount++;
	          			}
	          		}
	          	}
	          	self.elements=self.elementsActuality;
	          	self.element=self.elementsActuality[0];
	          }
	       	self.$nextTick(() => {
	  	      $(".nice-scroll").niceScroll(nice_scroll_classobj);
	  	      $(".nice-scroll")
	  	        .getNiceScroll()
	  	        .resize();
	        })
	        }
	      };
    	  //获取'统计信息'的'POI'明细信息并填充数据到页面上----方法(现状)
          mapPoiParam.sTypeIDList = [];
          mapPoiParam.iTagIDList = [];
          var poiActualityTypeSelectVal = self.params.poiActuality;
          for (var i = 0; i < poiActualityTypeSelectVal.length; i++) {
            if (poiActualityTypeSelectVal[i].checkall) {
              mapPoiParam.sTypeIDList.push(poiActualityTypeSelectVal[i].typeId);
            } else {
              for (var j = 0; j < poiActualityTypeSelectVal[i].tags.length; j++) {
                mapPoiParam.iTagIDList.push(
                		poiActualityTypeSelectVal[i].typeId +
                    ":" +
                    poiActualityTypeSelectVal[i].tags[j].tagName
                );
              }
            }
          }
          api.requestData.map.getPoiDetailsInfo(getPoiActualityDetails);
          //规划
          if(paramsData.poiPlan.length>0){
        	  self.situatPlanns.push('规划');
        	  //获取POI规划详情数据
        	  //获取'统计信息'的'POI'明细信息并填充数据到页面上----参数(规划)
    	      let getPoiPlanDetailsInfo = {
    	        data: mapPoiParam,
    	        callback: function(poiDetailsInfo) {
    	       	  if (poiDetailsInfo != 50001) {
    	            //console.log(poiDetailsInfo);
    	          	self.getPoiDetailsInfo.poiPlanDetails = poiDetailsInfo;
    	          	for(var i=0;i<poiDetailsInfo.length;i++){
    	          		if(self.elementsPlan.indexOf(poiDetailsInfo[i].cname)==-1){
    	          			self.elementsPlan.push(poiDetailsInfo[i].cname);
    	          			//poi数量
    	          			for(var j=0;j<poiDetailsInfo[i].boardDetailsResourceDtoList.length;j++){
    	          				self.poiCount++;
    	          			}
    	          		}
    	          	}
    	          }
    	       	self.$nextTick(() => {
    	  	      $(".nice-scroll").niceScroll(nice_scroll_classobj);
    	  	      $(".nice-scroll")
    	  	        .getNiceScroll()
    	  	        .resize();
    	        })
    	        }
    	      };
        	  //获取'统计信息'的'POI'明细信息并填充数据到页面上----方法(规划)
              mapPoiParam.sTypeIDList = [];
              mapPoiParam.iTagIDList = [];
              var poiPlanTypeSelectVal = self.params.poiPlan;
              for (var i = 0; i < poiPlanTypeSelectVal.length; i++) {
                if (poiPlanTypeSelectVal[i].checkall) {
                  mapPoiParam.sTypeIDList.push(poiPlanTypeSelectVal[i].typeId);
                } else {
                  for (var j = 0; j < poiPlanTypeSelectVal[i].tags.length; j++) {
                    mapPoiParam.iTagIDList.push(
                    		poiPlanTypeSelectVal[i].typeId +
                        ":" +
                        poiPlanTypeSelectVal[i].tags[j].tagName
                    );
                  }
                }
              }
              api.requestData.map.getPoiDetailsInfo(getPoiPlanDetailsInfo);
          }
      }else if(paramsData.poiPlan.length>0){
    	  //规划
    	  self.situatPlanns.push('规划');
    	  self.situatPlann='规划';
    	  //获取POI规划详情数据
    	  //获取'统计信息'的'POI'明细信息并填充数据到页面上----参数(规划)
	      let getPoiPlanDetailsInfo2 = {
	        data: mapPoiParam,
	        callback: function(poiDetailsInfo) {
	       	  if (poiDetailsInfo != 50001) {
	            //console.log(poiDetailsInfo);
	          	self.getPoiDetailsInfo.poiPlanDetails = poiDetailsInfo;
	          	self.getPoiDetailsInfo.poiDetailsInfo = poiDetailsInfo;
	          	for(var i=0;i<poiDetailsInfo.length;i++){
	          		if(self.elementsPlan.indexOf(poiDetailsInfo[i].cname)==-1){
	          			self.elementsPlan.push(poiDetailsInfo[i].cname);
	          			//poi数量
	          			for(var j=0;j<poiDetailsInfo[i].boardDetailsResourceDtoList.length;j++){
	          				self.poiCount++;
	          			}
	          		}
	          	}
	          	self.elements=self.elementsPlan;
	          	self.element=self.elementsPlan[0];
	          }
	       	self.$nextTick(() => {
	  	      $(".nice-scroll").niceScroll(nice_scroll_classobj);
	  	      $(".nice-scroll")
	  	        .getNiceScroll()
	  	        .resize();
	        })
	        }
	      };
    	  //获取'统计信息'的'POI'明细信息并填充数据到页面上----方法(规划)
          mapPoiParam.sTypeIDList = [];
          mapPoiParam.iTagIDList = [];
          var poiPlanTypeSelectVal2 = self.params.poiPlan;
          for (var i = 0; i < poiPlanTypeSelectVal2.length; i++) {
            if (poiPlanTypeSelectVal2[i].checkall) {
              mapPoiParam.sTypeIDList.push(poiPlanTypeSelectVal2[i].typeId);
            } else {
              for (var j = 0; j < poiPlanTypeSelectVal2[i].tags.length; j++) {
                mapPoiParam.iTagIDList.push(
                		poiPlanTypeSelectVal2[i].typeId +
                    ":" +
                    poiPlanTypeSelectVal2[i].tags[j].tagName
                );
              }
            }
          }
          api.requestData.map.getPoiDetailsInfo(getPoiPlanDetailsInfo2);
      }

      //根据'数据筛选'的'项目'选中情况分别获取'土地'、'住宅'和'商办'明细信息并分别填充数据----参数
      //土地
      let getMarketLandDetail = {
        data: mapParam,
        callback: function(landItemDetail) {
          if (landItemDetail != 50001) {
          	self.getLandItemDetail.landItemDetail = landItemDetail;
          }
          self.$nextTick(() => {
    	      $(".nice-scroll").niceScroll(nice_scroll_classobj);
    	      $(".nice-scroll")
    	        .getNiceScroll()
    	        .resize();
          })
             //console.log('土地');
          //console.log(landItemDetail);

          if(landItemDetail!="50001"){
			    let iconDataList=landItemDetail;
			    let gMap=map.gMap;
			   // gMap.removeOverlay('image');
				if (iconDataList && iconDataList.length > 0) {
					$.each(iconDataList, function (i, j) {
						let dealStatus='';
						let completionOrEndDateName='';
						let completionOrEndDate='';
						let closingOrInitFloorPriceName='';
						let closingOrInitFloorPrice=0;
						if(j.dealStatus=='已成交'){
							dealStatus='已成交';
							completionOrEndDateName='成交时间';
							completionOrEndDate=j.completionDate;
							closingOrInitFloorPriceName='成交楼面价';
							closingOrInitFloorPrice=j.closingFloorPrice;
						}else if(j.dealStatus=='未成交'){
							dealStatus='未成交';
							completionOrEndDateName='截止时间';
							completionOrEndDate=j.endDate;
							closingOrInitFloorPriceName='推出楼面价';
							closingOrInitFloorPrice=j.initialFloorPrice;
						}else if(j.dealStatus=='流拍'){
							dealStatus='流拍';
							completionOrEndDateName='截止时间';
							completionOrEndDate=j.endDate;
							closingOrInitFloorPriceName='推出楼面价';
							closingOrInitFloorPrice=j.initialFloorPrice;
						}else if(j.dealStatus=='交易变更'){
							dealStatus='交易变更';
							completionOrEndDateName='截止时间';
							completionOrEndDate=j.endDate;
							closingOrInitFloorPriceName='推出楼面价';
							closingOrInitFloorPrice=j.initialFloorPrice;
						}

						let png = self.choicepoiPng(dealStatus);
            //console.log(j);
						var id = 'land_point_' + j.parcelId;
						var point = cityGMap.PointService.Baidu.mercator([j.coordinateBX, j.coordinateBY]);
						var opts = {
							shape: 'image',
							size: [31, 36],
							offset: [-14, -36],
							image: cdnpath+contextPath + "/static/images/map-icon/"+png,
							id: id,
							point: point,
							clickable: true
						};

						var marker = new cityGMap.createMark(opts);
						marker.addEvent({
							click: function (e, data) {
								let aroundAnalysis={sObjectID:j.parcelId,sLat:j.coordinateBY,sLng:j.coordinateBX,sName:j.parcelName,sType:'土地'};
                let aroundAnalysisJson  = JSON.stringify(aroundAnalysis);
								// 添加弹窗
								var htmlStr = '<span class="btn-close">×</span>' +
									'<div class="tb_tit clearfix ">' +
									'<a href="javascript:;" class="fl col_white font12 mr05" title="' + (j.parcelName?j.parcelName:'--') + '">' + (j.parcelName?j.parcelName:'--') + '</a>' +
									'<span class="label label-primary sf-lable-trans fl mt05">' + dealStatus + '</span>' +
									'<span class="icon-tool-box fl ml05">' +
									'<em class="icon-eyes closed help-screwed"></em>' +
									'</span>' +
									'</div>' +
									'<div class="tb_cnt nice-scroll tbh nice-scroll">' +
									'<ul class="info-list no-padding no-margin">' +
									(j.parcelConforming ? '<li>' +
										'<div class="clearfix">' +
										'<span class="fl">规划用途：<span class="col_white">' + j.parcelConforming + '</span>&nbsp;</span>' +
										'</div>' +
										'</li>' : '') +
									(j.buildArea ? '<li>' +
										'<div class="clearfix">' +
										'<span class="fl">建设用地面积：<span class="col_white">' + j.buildArea + '</span>&nbsp;㎡</span>' +
										'</div>' +
										'</li>' : '') +
									(j.planningArea ? '<li>' +
										'<div class="clearfix">' +
										'<span class="fl">规划建筑面积：<span class="col_white">' + j.planningArea + '</span>&nbsp;㎡</span>' +
										'</div>' +
										'</li>' : '') +
									(j.planningAreaOfHouse ? '<li>' +
										'<div class="clearfix">' +
										'<span class="fl">住宅类用地规划建筑面积：<span class="col_white">' +j.planningAreaOfHouse + '</span>&nbsp;㎡</span>' +
										'</div>' +
										'</li>' : '') +
									(j.planningAreaOfCommerce ? '<li>' +
										'<div class="clearfix">' +
										'<span class="fl">商业用地规划建筑面积：<span class="col_white">' + j.planningAreaOfCommerce + '</span>&nbsp;㎡</span>' +
										'</div>' +
										'</li>' : '') +

									(j.planningAreaOfBusiness ? '<li>' +
										'<div class="clearfix">' +
										'<span class="fl">商业住宅部分规划建筑面积：<span class="col_white">' + j.planningAreaOfBusiness + '</span>&nbsp;㎡</span>' +
										'</div>' +
										'</li>' : '') +
									(completionOrEndDate ? '<li>' +
										'<div class="clearfix">' +
										'<span class="fl">'+completionOrEndDateName+'：<span class="col_white">' + completionOrEndDate + '</span>&nbsp;</span>' +
										'</div>' +
										'</li>' : '') +
									(j.closingCost ? '<li>' +
										'<div class="clearfix">' +
										'<span class="fl">土地出让金：<span class="col_white">' + j.closingCost + '</span>&nbsp;万元</span>' +
										'</div>' +
										'</li>' : '') +
									(closingOrInitFloorPrice!=0 ? '<li>' +
										'<div class="clearfix">' +
										'<span class="fl">'+closingOrInitFloorPriceName+'：<span class="col_white">' + closingOrInitFloorPrice + '</span>&nbsp;元/㎡</span>' +
										'</div>' +
										'</li>' : '') +
									(j.avgPremiumRate ? '<li>' +
										'<div class="clearfix">' +
										'<span class="fl">溢价率：<span class="col_white">' + j.avgPremiumRate + '</span>&nbsp;%</span>' +
										'</div>' +
										'</li>' : '') +
									(j.transferee.trim() ? '<li>' +
										'<div class="clearfix">' +
										'<span class="fl">受让单位：<span class="col_white">' + j.transferee + '</span>&nbsp;</span>' +
										'</div>' +
										'</li>' : '') +
									'</ul>' +
									'</div>'
								  //  + '<div class="tb_bot clearfix butbox">'
						    	// 		+ '<a href="javascript:;" data-target='+JSON.stringify(aroundAnalysis)+' class="but_confirm  fr mr10">周边分析</a>'
						    	//    + '</div>'
                  ;
								var id = 'landTip_point_' + e.param.point[0] + "_" + e.param.point[1];
								mapIconLandTips = gMap.tips || {};
								// 隐藏上次的tip
								var ifExist = false;
								for (var k in mapIconLandTips) {
									if (k != id && !mapIconLandTips[k].hidden && !mapIconLandTips[k].opts.screwed) {
										mapIconLandTips[k].hide();
									}
									if (k == id && mapIconLandTips[k].hidden) {
										mapIconLandTips[k].show();
										ifExist = true;
									}
								}
								if (ifExist) {
									return;
								}

								var mapIconTip = new cityGMap.tip(gMap, {
									type: 'mapTip',
									id: id,
									point: point,
									innerHtml: htmlStr,

									className: 'suspendbox',
									draggable: 'enable',
									eventListener: {
										mouseover: function (e, t) {
											t.$node.find('.btn-close').show();
										},
										mouseout: function (e, t) {
											t.$node.find('.btn-close').hide();
										},
										close: function (e) {
											e.parent().remove();
										}
									}
								});
								gMap.addMapTip(mapIconTip);
								$(".nice-scroll").niceScroll(nice_scroll_classobj);


              //   $('#'+id+' a[data-target]').click(function(){
              //     let  json=$(this).data("target");
              //       console.log(obj);
              //     // let  json = obj;
              //       let form = document.createElement('form');
              //       document.body.appendChild(form);
              //       form.target = '_blank';
              //       form.method = 'post';
              //       form.enctype="multipart/form-data";
              //       form.action = contextPath+'/investmentproject/1/index';
              //       try {
              //           form.appendChild(createInput('analysisInfo',  $.toJSON(json)));
              //           form.submit();
              //           document.body.removeChild(form);
              //       } catch (e) {

              //       }
              //   })
							 },
							    mouseover: function () {
                                  marker.layer.options.size = [46,54];
                                  gMap.removeOverlay('image', id);
                                  gMap.addOverlay('image', id, marker);
						                },
										mouseout: function () {
                                 marker.layer.options.size = [31,36];
                                  gMap.removeOverlay('image', id);
                                  gMap.addOverlay('image', id, marker);
                    }
						});

						gMap.addOverlay('image', id, marker);

					});
				}
		  }else{
			  //TODO没数据
		  }

        self.myLoading = false;
        }
      };
      //房产
      //新房住宅
      let getNewHouseResidenceDetails = {
        data: mapHouseParam,
        callback: function(newHouseResidenceDetails) {
          if (newHouseResidenceDetails != 50001) {
	          self.getNewHouseResidenceDetails.newHouseResidenceDetails = newHouseResidenceDetails;
            self.getNewhouseIcon(newHouseResidenceDetails);
          }
          self.$nextTick(() => {
    	      $(".nice-scroll").niceScroll(nice_scroll_classobj);
    	      $(".nice-scroll")
    	        .getNiceScroll()
    	        .resize();
          })
             //console.log('新房');
          //console.log(newHouseResidenceDetails);
          self.myLoading = false;
        }
      };
      //二手房住宅
      let getSecondHouseResidenceDetails = {
        data: mapHouseParam,
        callback: function(secondHouseResidenceDetails) {
          if (secondHouseResidenceDetails != 50001) {
	          self.getSecondHouseResidenceDetails.secondHouseResidenceDetails = secondHouseResidenceDetails;
            self.getEsfhouseIcon(secondHouseResidenceDetails);
          }
          self.$nextTick(() => {
    	      $(".nice-scroll").niceScroll(nice_scroll_classobj);
    	      $(".nice-scroll")
    	        .getNiceScroll()
    	        .resize();
          })
              //console.log('二手房');
          //console.log(secondHouseResidenceDetails);
          self.myLoading = false;
        }
      };
      //新房商铺
      let getNewHouseShopDetails = {
        data: mapHouseParam,
        callback: function(newHouseShopDetails) {
          //console.log("newHouseShopDetails----");
          //console.log(newHouseShopDetails);
          if (newHouseShopDetails != 50001) {
	          self.getNewHouseShopDetails.newHouseShopDetails = newHouseShopDetails;
            self.getShopsIcon(newHouseShopDetails,'shops');
          }
          self.$nextTick(() => {
    	      $(".nice-scroll").niceScroll(nice_scroll_classobj);
    	      $(".nice-scroll")
    	        .getNiceScroll()
    	        .resize();
          })
            //console.log('新房商铺');
            //console.log(newHouseShopDetails);
            self.myLoading = false;
        }
      };
      //二手房商铺
      let getSecondHouseShopDetails = {
        data: mapHouseParam,
        callback: function(secondHouseShopDetails) {
          //console.log("secondHouseShopDetails----");
          //console.log(secondHouseShopDetails);
          if (secondHouseShopDetails != 50001) {
	          self.getSecondHouseShopDetails.secondHouseShopDetails = secondHouseShopDetails;
           self.getShopsIcon(secondHouseShopDetails,'shops');
          }
          self.$nextTick(() => {
    	      $(".nice-scroll").niceScroll(nice_scroll_classobj);
    	      $(".nice-scroll")
    	        .getNiceScroll()
    	        .resize();
          })

             //console.log('二手房房商铺');
            //console.log(secondHouseShopDetails);
            self.myLoading = false;
        }
      };
      //新房写字楼
      let getNewHouseOfficeDetails = {
        data: mapHouseParam,
        callback: function(newHouseOfficeDetails) {
          //console.log("newHouseOfficeDetails----");
          //console.log(newHouseOfficeDetails);
          if (newHouseOfficeDetails != 50001) {
	          self.getNewHouseOfficeDetails.newHouseOfficeDetails = newHouseOfficeDetails;
            self.getShopsIcon(newHouseOfficeDetails,'office');
          }
          self.$nextTick(() => {
    	      $(".nice-scroll").niceScroll(nice_scroll_classobj);
    	      $(".nice-scroll")
    	        .getNiceScroll()
    	        .resize();
          })

             //console.log('新房写字楼');
            //console.log(newHouseOfficeDetails);
            self.myLoading = false;
        }
      };
      //二手房写字楼
      let getSecondHouseOfficeDetails = {
        data: mapHouseParam,
        callback: function(secondHouseOfficeDetails) {
          //console.log("secondHouseOfficeDetails----");
          //console.log(secondHouseOfficeDetails);
          if (secondHouseOfficeDetails != 50001) {
	          self.getSecondHouseOfficeDetails.secondHouseOfficeDetails = secondHouseOfficeDetails;
            self.getShopsIcon(secondHouseOfficeDetails,'office');
          }
          self.$nextTick(() => {
    	      $(".nice-scroll").niceScroll(nice_scroll_classobj);
    	      $(".nice-scroll")
    	        .getNiceScroll()
    	        .resize();
          })

             //console.log('二手房写字楼');
            //console.log(secondHouseOfficeDetails);
            self.myLoading = false;
        }
      };

      //根据'数据筛选'的'项目'选中情况分别获取'土地'、'住宅'和'商办'明细信息并分别填充数据(填充数据时直接定义点击名称获取详情信息并填充详细信息数据)----方法
      var projectArr = self.params.project;
      for (var i = 0; i < projectArr.length; i++) {
        if (projectArr[i] == "land") {
          //土地
          api.requestData.map.getMarketLandDetail(getMarketLandDetail);
        } else if (projectArr[i] == "newhouse") {
          //新房住宅
          /* mapHouseParam.houseType = "newHousing";
          mapHouseParam.propertyType = "1,2";
          mapHouseParam.propertyType2 = "住宅,别墅";
          api.requestData.map.getNewHouseResidenceDetails(
            getNewHouseResidenceDetails
          ); */
          self.getNewhouseIcon(
                  self.getNewHouseResidenceDetails.newHouseResidenceDetails
                );
        } else if (projectArr[i] == "esfhouse") {
          //二手房住宅
          /* mapHouseParam.propertyType = "住宅,别墅";
          api.requestData.map.getSecondHouseResidenceDetails(
            getSecondHouseResidenceDetails
          ); */
          self.getEsfhouseIcon(
                  self.getSecondHouseResidenceDetails.secondHouseResidenceDetails
                );
        } else if (projectArr[i] == "shops") {
          //新房商铺
          /* mapHouseParam.houseType = "newHousing";
          mapHouseParam.propertyType = "8";
          mapHouseParam.propertyType2 = "商铺";
          api.requestData.map.getNewHouseShopDetails(getNewHouseShopDetails);
          //二手房商铺
          mapHouseParam.propertyType = "商铺";
          api.requestData.map.getSecondHouseShopDetails(
            getSecondHouseShopDetails
          ); */
        	self.getShopsIcon(
                    self.getNewHouseShopDetails.newHouseShopDetails,
                    "shops"
                  );
        	self.getShopsIcon(
                    self.getSecondHouseShopDetails.secondHouseShopDetails,
                    "shops"
                  );
        } else if (projectArr[i] == "offices") {
          //新房写字楼
          /* mapHouseParam.houseType = "newHousing";
          mapHouseParam.propertyType = "6";
          mapHouseParam.propertyType2 = "写字楼";
          api.requestData.map.getNewHouseOfficeDetails(
            getNewHouseOfficeDetails
          );
          //二手房写字楼
          mapHouseParam.propertyType = "写字楼";
          api.requestData.map.getSecondHouseOfficeDetails(
            getSecondHouseOfficeDetails
          ); */
        	self.getShopsIcon(
                self.getNewHouseOfficeDetails.newHouseOfficeDetails,
                "office"
              );
            //二手房写字楼
            self.getShopsIcon(
              self.getSecondHouseOfficeDetails.secondHouseOfficeDetails,
              "office"
            );
        }
      }

    },
    //下载
    gMapDown:function () {
		 map.gMap.downloadImg({containment:'parent'})
	 },
	//缩小
	zoomOut: function () {
		 map.gMap.gmap.zoomOut();
	 },
	//放大
	zoomIn: function () {
	        map.gMap.gmap.zoomIn();
          },
  choicepoiPng(sType) {
	  let png = '';
	  switch(sType)
    {
      case '现状轨道交通':
        png = 'i_icon_subway.png';
        break;
      case '规划轨道交通':
        png = 'i_icon_subway_plan.png';
        break;
      case '现状公交':
        png = 'i_icon_bus.png';
        break;
      case '规划公交':
        png = 'i_icon_bus_plan.png';
        break;
      case '现状火车':
        png = 'i_icon_train.png';
        break;
      case '规划火车':
        png = 'i_icon_train_plan.png';
        break;
      case '现状机场':
        png = 'i_icon_plain.png';
        break;
      case '规划机场':
        png = 'i_icon_plain_plan.png';
        break;
      case '现状幼儿园':
        png = 'i_icon_kindergarten.png';
        break;
      case '规划幼儿园':
        png = 'i_icon_kindergarten_plan.png';
        break;
      case '现状小学':
        png = 'i_icon_primarySchool.png';
        break;
      case '规划小学':
        png = 'i_icon_primarySchool_plan.png';
        break;
      case '现状中学':
        png = 'i_icon_highSchool.png';
        break;
      case '规划中学':
        png = 'i_icon_highSchool_plan.png';
        break;
      case '现状大学':
        png = 'i_icon_university.png';
        break;
      case '规划大学':
        png = 'i_icon_university_plan.png';
        break;
      case '现状医院':
        png = 'i_icon_hospital.png';
        break;
      case '规划医院':
        png = 'i_icon_hospital_plan.png';
        break;
      case '现状商场':
        png = 'i_icon_market.png';
        break;
      case '规划商场':
        png = 'i_icon_market_plan.png';
        break;
      case '现状超市':
        png = 'i_icon_largeMarket.png';
        break;
      case '规划超市':
        png = 'i_icon_largeMarket_plan.png';
        break;
      case '现状公园景点':
        png = 'i_icon_park.png';
        break;
      case '规划公园景点':
        png = 'i_icon_park_plan.png';
        break;
      case '现状人文':
        png = 'i_icon_culture.png';
        break;
      case '规划人文':
        png = 'i_icon_culture_plan.png';
        break;
      case '现状区位要素':
        png = 'i_icon_locationElement.png';
        break;
      case '规划区位要素':
        png = 'i_icon_locationElement_plan.png';
        break;
      case '现状殡仪服务':
        png = 'i_icon_funeralParlor.png';
        break;
      case '规划殡仪服务':
        png = 'i_icon_funeralParlor_plan.png';
        break;
      case '现状垃圾场':
        png = 'i_icon_garbageStation.png';
        break;
      case '规划垃圾场':
        png = 'i_icon_garbageStation_plan.png';
        break;
      case '现状污水处理厂':
        png = 'i_icon_sewageTreatment.png';
        break;
      case '规划污水处理厂':
        png = 'i_icon_sewageTreatment_plan.png';
        break;
      case '现状热电厂':
        png = 'i_icon_thermalPowerPlant.png';
        break;
      case '规划热电厂':
        png = 'i_icon_thermalPowerPlant_plan.png';
        break;
      case '现状印刷厂':
        png = 'i_icon_printingPlant.png';
        break;
      case '规划印刷厂':
        png = 'i_icon_printingPlant_plan.png';
        break;
      case '现状印染厂':
        png = 'i_icon_printingDyeing.png';
        break;
      case '规划印染厂':
        png = 'i_icon_printingDyeing_plan.png';
        break;
      case '现状化工厂':
        png = 'i_icon_chemicalPlant.png';
        break;
      case '规划化工厂':
        png = 'i_icon_chemicalPlant_plan.png';
        break;
      case '现状加油加气站':
        png = 'i_icon_GAS.png';
        break;
      case '规划加油加气站':
        png = 'i_icon_GAS_plan.png';
        break;
      case '现状加工厂':
        png = 'i_icon_processfactory.png';
        break;
      case '规划加工厂':
        png = 'i_icon_processfactory_plan.png';
        break;
      case '未成交':
        png = 'i_icon_land_notTraded.png';
        break;
      case '已成交':
        png = 'i_icon_land_traded.png';
        break;
      case '流拍':
        png = 'i_icon_land_auction.png';
        break;
      case '交易变更':
        png = 'i_icon_land_transactionChange.png';
        break;
      case '新房在售住宅':
        png = 'i_icon_house_new_inSale.png';
        break;
      case '新房未售住宅':
        png = 'i_icon_house_new_unSold.png';
        break;
      case '新房售罄住宅':
        png = 'i_icon_house_new_runOut.png';
        break;
      case '新房在售别墅':
        png = 'i_icon_villa_new_inSale.png';
        break;
      case '新房未售别墅':
        png = 'i_icon_villa_new_unSold.png';
        break;
      case '新房售罄别墅':
        png = 'i_icon_villa_new_runOut.png';
        break;
      case '二手房住宅':
        png = 'i_icon_house_secondhand.png';
        break;
      case '二手房别墅':
        png = 'i_icon_villa_secondHand.png';
        break;
      case '商铺在售':
        png = 'i_icon_shops_new_inSale.png';
        break;
      case '商铺在租':
        png = 'i_icon_shops_new_inRent.png';
        break;
      case '商铺未售':
        png = 'i_icon_shops_new_unSold.png';
        break;
      case '商铺租售':
        png = 'i_icon_shops_new_rentAndsell.png';
        break;
      case '商铺售罄':
        png = 'i_icon_shops_new_runOut.png';
        break;
      case '写字楼在售':
        png = 'i_icon_officeBuild_new_inSale.png';
        break;
      case '现状写字楼':
        png = 'i_icon_officeBuild_new_inSale.png';
        break;
      case '规划写字楼':
        png = 'i_icon_officeBuild_new_unSold.png';
        break;
      case '写字楼在租':
        png = 'i_icon_officeBuild_new_inRent.png';
        break;
      case '写字楼未售':
        png = 'i_icon_officeBuild_new_unSold.png';
        break;
      case '写字楼租售':
        png = 'i_icon_officeBuild_new_rentAndsell.png';
        break;
      case '写字楼售罄':
        png = 'i_icon_officeBuild_new_runOut.png';
        break;
      default :
        png = 'i_icon_default.png';
        break;
    }
    return png;
  },
    getPoiIcon(dataListPoi){
		  var self=this;
      let gMap = map.gMap;
      	if (dataListPoi && dataListPoi.length > 0) {
								$.each(dataListPoi, function (i, j) {
                  let png = self.choicepoiPng(j.sType);
									var id = 'poi_point_' + j.iID;
									var point = cityGMap.PointService.Baidu.mercator([j.sBDLng, j.sBDLat]);
									var opts = {
										shape: 'image',
										size: [31, 36],
										offset: [-14, -36],
										image: cdnpath+contextPath + "/static/images/map-icon/"+png,
										id: id,
										point: point,
										clickable: true
									};

									var marker = new cityGMap.createMark(opts);

									marker.addEvent({
										click: function (e, data) {
                      let stype = j.sType.substring(2);
                      let saddress = (j.sAddress)?(j.sAddress):'-';
											// 添加弹窗
											let htmlStr = `<span class="btn-close">×</span>
                              <div class="tb_tit clearfix">
                                  <a href="javascript:;" class="fl col_white font12 mr05" title="${j.sName}">${j.sName}</a>
                              </div>
                              <div class="tb_cnt nice-scroll tbh">
                                  <ul class="info-list no-padding no-margin">
                                      <li>
                                          <div class="clearfix">
                                              <span class="fl">类别：${stype}</span>
                                          </div>
                                      </li>
                                      <li>
                                          <div class="clearfix">
                                              <span class="fl">级别：${j.sTag}</span>
                                              </span>
                                          </div>
                                      </li>
                                      <li>
                                          <div class="clearfix">
                                              <span class="fl">地址：${saddress}</span>
                                              </span>
                                          </div>
                                      </li>
                                  </ul>
                              </div>`;
											var id = 'poiTip_point_' + e.param.point[0] + "_" + e.param.point[1];
										  mapIconLandTips = gMap.tips || {};
											//隐藏上次的tip
											var ifExist = false;
											for (var k in mapIconLandTips) {
												if (k != id && !mapIconLandTips[k].hidden && !mapIconLandTips[k].opts.screwed) {
													mapIconLandTips[k].hide();
												}
												if (k == id && mapIconLandTips[k].hidden) {
													mapIconLandTips[k].show();
													ifExist = true;
												}
											}
											if (ifExist) {
												return;
										 	}

											var mapIconTip = new cityGMap.tip(gMap, {
												type: 'mapTip',
												id: id,
												point: point,
												innerHtml: htmlStr,

												className: 'suspendbox',
												draggable: 'enable',
												eventListener: {
													mouseover: function (e, t) {
														t.$node.find('.btn-close').show();
													},
													mouseout: function (e, t) {
														t.$node.find('.btn-close').hide();
													},
													close: function (e) {
														e.parent().remove();
													}
												}
											});
											gMap.addMapTip(mapIconTip);
											$(".nice-scroll").niceScroll(nice_scroll_classobj);
										},
										mouseover: function () {
                                  marker.layer.options.size = [46,54];
                                  gMap.removeOverlay('image', id);
                                  gMap.addOverlay('image', id, marker);
						                },
										mouseout: function () {
                                 marker.layer.options.size = [31,36];
                                  gMap.removeOverlay('image', id);
                                  gMap.addOverlay('image', id, marker);
                    }
									});

									gMap.addOverlay('image', id, marker);
								});
            }
        },
    getNewhouseIcon(dataListPoi){
		  var self=this;
      let gMap = map.gMap;
      	if (dataListPoi && dataListPoi.length > 0) {
								$.each(dataListPoi, function (i, j) {
                  let png = self.choicepoiPng('新房'+j.saleStatus+j.propertyType);
									var id = 'newHouse_point_' + j.projectId;
									var point = cityGMap.PointService.Baidu.mercator([j.axisX, j.axisY]);
									var opts = {
										shape: 'image',
										size: [31, 36],
										offset: [-14, -36],
										image: cdnpath+contextPath + "/static/images/map-icon/"+png,
										id: id,
										point: point,
										clickable: true
									};

									var marker = new cityGMap.createMark(opts);

									marker.addEvent({
										click: function (e, data) {
											// 添加弹窗
                      let structureArea = parseFloat(j.structureArea/10000).toFixed(2);
											let htmlStr =  '<span class="btn-close">×</span>'+
	                     '<div class="tb_tit clearfix">'+
	 						'<a href="javascript:;" class="fl col_white font12 mr05">'+j.projectName+'</a>'+
	 						'<span class="label label-primary sf-lable-trans fl mt05">新房</span>'+
	 						'<span class="icon-tool-box fl ml05">'+
	         			  	'<em class="icon-eyes closed help-screwed"></em>'+
	         			   '</span>'+
	                     '</div>'+
	                     '<div class="tb_cnt nice-scroll tbh">'+
	                         '<ul class="info-list no-padding no-margin">'+
	 						(j.unitPrice?'<li>'+
	                                 '<div class="clearfix">'+
	                                     '<span class="fl">成交均价：<span class="col_white">'+j.unitPrice+'</span>&nbsp;元/㎡</span>'+
	                                 '</div>'+
	                             '</li>'+
	                             '<li>'+
	                                 '<div class="clearfix">'+
                  '<span class="fl">成交面积：<span class="col_white">' +
                  (j.dealArea && j.dealArea != "null" ? j.dealArea : "--") +
                  "</span>&nbsp;万㎡（" +
                  (j.dealUnits && j.dealUnits != "null" ? j.dealUnits : "--") +
                  "套）</span>" +
	                                 '</div>'+
	                              '</li>' : '')+
	 						(j.listedArea?'<li>'+
	                                 '<div class="clearfix">'+
	                                     '<span class="fl">上市面积：<span class="col_white">'+j.listedArea+'</span>&nbsp;万㎡（'+j.listedUnits+'套）</span>'+
	                                 '</div>'+
	                              '</li>': '')+
	 						(j.saledArea?'<li>'+
	                                 '<div class="clearfix">' +
	                                     '<span class="fl">可售面积：<span class="col_white">'+j.saledArea+'</span>&nbsp;万㎡（'+j.saledUnits+'套）</span>'+
	                                 '</div>'+
	                              '</li>' : '')+
								'<li>'+
	                                 '<div class="clearfix">'+
	                                     '<span class="fl">物业类别：<span class="col_white">'+(j.propertyType?j.propertyType:"住宅")+'</span>&nbsp;</span>'+
	                                 '</div>'+
	                              '</li>'+
	 							 '<li>'+
	                                 '<div class="clearfix">'+
	                                     '<span class="fl">项目特色：<span class="col_white">'+(j.characteristic?j.characteristic:"无")+'</span>&nbsp;</span>'+
	                                 '</div>'+
	                              '</li>' +
	 						      '<li>'+
	                                 '<div class="clearfix">'+
	                                     '<span class="fl">建筑面积：<span class="col_white">'+structureArea+'</span>&nbsp;万㎡</span>'+
	                                 '</div>'+
	                              '</li>' +
								  '<li>'+
	                                 '<div class="clearfix">'+
	                                     '<span class="fl">开发公司：<span class="col_white">'+(j.developer?j.developer:"无")+'</span>&nbsp;</span>'+
	                                 '</div>'+
	                              '</li>' +


	                         '</ul>'+
	                     '</div>';
											var id = 'newhouseTip_point_' + e.param.point[0] + "_" + e.param.point[1];
										  mapIconLandTips = gMap.tips || {};
											//隐藏上次的tip
											var ifExist = false;
											for (var k in mapIconLandTips) {
												if (k != id && !mapIconLandTips[k].hidden && !mapIconLandTips[k].opts.screwed) {
													mapIconLandTips[k].hide();
												}
												if (k == id && mapIconLandTips[k].hidden) {
													mapIconLandTips[k].show();
													ifExist = true;
												}
											}
											if (ifExist) {
												return;
										 	}

											var mapIconTip = new cityGMap.tip(gMap, {
												type: 'mapTip',
												id: id,
												point: point,
												innerHtml: htmlStr,

												className: 'suspendbox',
												draggable: 'enable',
												eventListener: {
													mouseover: function (e, t) {
														t.$node.find('.btn-close').show();
													},
													mouseout: function (e, t) {
														t.$node.find('.btn-close').hide();
													},
													close: function (e) {
														e.parent().remove();
													}
												}
											});
											gMap.addMapTip(mapIconTip);
											$(".nice-scroll").niceScroll(nice_scroll_classobj);
										},
										mouseover: function () {
                                  marker.layer.options.size = [46,54];
                                  gMap.removeOverlay('image', id);
                                  gMap.addOverlay('image', id, marker);
						                },
										mouseout: function () {
                                 marker.layer.options.size = [31,36];
                                  gMap.removeOverlay('image', id);
                                  gMap.addOverlay('image', id, marker);
                    }
									});

									gMap.addOverlay('image', id, marker);
								});
        }
      	self.myLoading = false;
			},
    getEsfhouseIcon(dataListPoi){
		  var self=this;
      let gMap = map.gMap;
      //gMap.removeOverlay('image');
      	if (dataListPoi && dataListPoi.length > 0) {
								$.each(dataListPoi, function (i, j) {
                  //console.log(j);
                  let png = self.choicepoiPng('二手房'+j.propertyType);
									j.sID = i;
									var id = 'esfHouse_point_' + j.projectId;
									var point = cityGMap.PointService.Baidu.mercator([j.axisX, j.axisY]);
									var opts = {
										shape: 'image',
										size: [31, 36],
										offset: [-14, -36],
										image: cdnpath+contextPath + "/static/images/map-icon/"+png,
										id: id,
										point: point,
										clickable: true
									};

									var marker = new cityGMap.createMark(opts);

									marker.addEvent({
										click: function (e, data) {
											// 添加弹窗
											let htmlStr = `
							<span class="btn-close" style="display: none;">×</span>
							<div class="tb_tit clearfix">
								<a href="javascript:;" class="fl col_white font12 mr05">${j.projectName}</a>
								<span class="label label-primary sf-lable-trans fl mt05">二手房</span>
								<span class="icon-tool-box fl ml05">
								<em class="icon-eyes closed help-screwed"></em>
							   </span>
							</div>
							<div class="tb_cnt nice-scroll tbh">
								<ul class="info-list no-padding no-margin">`
									+(j.unitPrice?`<li ><div class="clearfix"><span class="fl">小区均价 ：</span><span class="col_white"> ${j.unitPrice}元/㎡</span></div></li>`:'')
									+(j.dealArea?`<li ><div class="clearfix"><span class="fl">成交面积 ：</span><span class="col_white"> ${j.dealArea}万㎡</span></div></li>`:'')
									+(j.propertyType?`<li ><div class="clearfix"><span class="fl">物业类型 ：</span><span class="col_white"> ${j.propertyType}</span></div></li>`:'')
									+(j.structureAge?`<li ><div class="clearfix"><span class="fl">建筑年代 ：</span><span class="col_white"> ${j.structureAge}年</span></div></li>`:'')
									+(j.structureArea?`<li ><div class="clearfix"><span class="fl">建筑面积 ：</span><span class="col_white"> ${j.structureArea}万㎡</span></div></li>`:'')
									+(j.developer?`<li ><div class="clearfix"><span class="fl">开发公司 ：</span><span class="col_white"> ${j.developer}</span></div></li>`:'')+`
								</ul>
							</div>
						`;
											var id = 'esfhouseTip_point_' + e.param.point[0] + "_" + e.param.point[1];
										  mapIconLandTips = gMap.tips || {};
											//隐藏上次的tip
											var ifExist = false;
											for (var k in mapIconLandTips) {
												if (k != id && !mapIconLandTips[k].hidden && !mapIconLandTips[k].opts.screwed) {
													mapIconLandTips[k].hide();
												}
												if (k == id && mapIconLandTips[k].hidden) {
													mapIconLandTips[k].show();
													ifExist = true;
												}
											}
											if (ifExist) {
												return;
										 	}

											var mapIconTip = new cityGMap.tip(gMap, {
												type: 'mapTip',
												id: id,
												point: point,
												innerHtml: htmlStr,

												className: 'suspendbox',
												draggable: 'enable',
												eventListener: {
													mouseover: function (e, t) {
														t.$node.find('.btn-close').show();
													},
													mouseout: function (e, t) {
														t.$node.find('.btn-close').hide();
													},
													close: function (e) {
														e.parent().remove();
													}
												}
											});
											gMap.addMapTip(mapIconTip);
											$(".nice-scroll").niceScroll(nice_scroll_classobj);
										},
										mouseover: function () {
                                  marker.layer.options.size = [46,54];
                                  gMap.removeOverlay('image', id);
                                  gMap.addOverlay('image', id, marker);
						                },
										mouseout: function () {
                                 marker.layer.options.size = [31,36];
                                  gMap.removeOverlay('image', id);
                                  gMap.addOverlay('image', id, marker);
                    }
									});

									gMap.addOverlay('image', id, marker);
								});
            }

      	   self.myLoading = false;
        },
    getShopsIcon(dataListPoi,target){
		  var self=this;
      let gMap = map.gMap;
      	if (dataListPoi && dataListPoi.length > 0) {
								$.each(dataListPoi, function (i, j) {
                  let png = '';
                  let text = '';
                  if(target == 'shops') {
                    if(j.saleStatus) {
                      png = self.choicepoiPng('商铺'+j.saleStatus);
                    }else {
                      png = self.choicepoiPng('商铺在售');
                    }
                    text = '商铺';
                  }else {
                     if(j.saleStatus) {
                      png = self.choicepoiPng('写字楼'+j.saleStatus);
                    }else {
                      png = self.choicepoiPng('写字楼在售');
                    }
                    text = '写字楼';
                  }
									var id = 'shops_point_' + j.projectId;
									var point = cityGMap.PointService.Baidu.mercator([j.axisX, j.axisY]);
									var opts = {
										shape: 'image',
										size: [31, 36],
										offset: [-14, -36],
										image: cdnpath+contextPath + "/static/images/map-icon/"+png,
										id: id,
										point: point,
										clickable: true
									};

									var marker = new cityGMap.createMark(opts);

									marker.addEvent({
										click: function (e, data) {
											// 添加弹窗
                      let structureArea =j.structureArea?parseFloat(j.structureArea / 10000).toFixed(2):'--';
											let htmlStr = '<span class="btn-close">×</span>'+
	                     '<div class="tb_tit clearfix">'+
	 						'<a href="javascript:;" class="fl col_white font12 mr05">'+j.projectName+'</a>'+
	 						'<span class="label label-primary sf-lable-trans fl mt05">'+text+'</span>'+
	 						'<span class="icon-tool-box fl ml05">'+
	         			  	'<em class="icon-eyes closed help-screwed"></em>'+
	         			   '</span>'+
	                     '</div>'+
	                     '<div class="tb_cnt nice-scroll tbh">'+
	                         '<ul class="info-list no-padding no-margin">'+
	 						(j.unitPrice?'<li>'+
	                                 '<div class="clearfix">'+
	                                     '<span class="fl">成交均价：<span class="col_white">'+j.unitPrice+'</span>&nbsp;元/㎡</span>'+
	                                 '</div>'+
	                             '</li>'+
	                             '<li>'+
	                                 '<div class="clearfix">'+
	                                  '<span class="fl">成交面积：<span class="col_white">' +
	                                     (j.dealArea && j.dealArea != "null" ? j.dealArea : "--") +
	                                     "</span>&nbsp;万㎡（" +
	                                     (j.dealUnits && j.dealUnits != "null" ? j.dealUnits : "--") +
	                                     "套）</span>"+
	                                 '</div>'+
	                              '</li>' : '')+
	 						(j.listedArea?'<li>'+
	                                 '<div class="clearfix">'+
	                                     '<span class="fl">上市面积：<span class="col_white">'+j.listedArea+'</span>&nbsp;万㎡（'+j.listedUnits+'套）</span>'+
	                                 '</div>'+
	                              '</li>': '')+
	 						(j.saledArea?'<li>'+
	                                 '<div class="clearfix">' +
	                                     '<span class="fl">可售面积：<span class="col_white">'+j.saledArea+'</span>&nbsp;万㎡（'+j.saledUnits+'套）</span>'+
	                                 '</div>'+
	                              '</li>' : '')+
								'<li>'+
	                                 '<div class="clearfix">'+
	                                     '<span class="fl">物业类别：<span class="col_white">'+(j.propertyType?j.propertyType:"住宅")+'</span>&nbsp;</span>'+
	                                 '</div>'+
	                              '</li>'+
	 							 '<li>'+
	                                 '<div class="clearfix">'+
	                                     '<span class="fl">项目特色：<span class="col_white">'+(j.characteristic?j.characteristic:"无")+'</span>&nbsp;</span>'+
	                                 '</div>'+
	                              '</li>' +
	 						      '<li>'+
	                                 '<div class="clearfix">'+
	                                     '<span class="fl">建筑面积：<span class="col_white">'+structureArea+'</span>&nbsp;万㎡</span>'+
	                                 '</div>'+
	                              '</li>' +
								  '<li>'+
	                                 '<div class="clearfix">'+
	                                     '<span class="fl">开发公司：<span class="col_white">'+(j.developer?j.developer:"无")+'</span>&nbsp;</span>'+
	                                 '</div>'+
	                              '</li>' +


	                         '</ul>'+
	                     '</div>';;
											var id = 'shopTip_point_' + e.param.point[0] + "_" + e.param.point[1];
										  mapIconLandTips = gMap.tips || {};
											//隐藏上次的tip
											var ifExist = false;
											for (var k in mapIconLandTips) {
												if (k != id && !mapIconLandTips[k].hidden && !mapIconLandTips[k].opts.screwed) {
													mapIconLandTips[k].hide();
												}
												if (k == id && mapIconLandTips[k].hidden) {
													mapIconLandTips[k].show();
													ifExist = true;
												}
											}
											if (ifExist) {
												return;
										 	}

											var mapIconTip = new cityGMap.tip(gMap, {
												type: 'mapTip',
												id: id,
												point: point,
												innerHtml: htmlStr,

												className: 'suspendbox',
												draggable: 'enable',
												eventListener: {
													mouseover: function (e, t) {
														t.$node.find('.btn-close').show();
													},
													mouseout: function (e, t) {
														t.$node.find('.btn-close').hide();
													},
													close: function (e) {
														e.parent().remove();
													}
												}
											});
											gMap.addMapTip(mapIconTip);
											$(".nice-scroll").niceScroll(nice_scroll_classobj);
										},
										mouseover: function () {
                                  marker.layer.options.size = [46,54];
                                  gMap.removeOverlay('image', id);
                                  gMap.addOverlay('image', id, marker);
						                },
										mouseout: function () {
                                 marker.layer.options.size = [31,36];
                                  gMap.removeOverlay('image', id);
                                  gMap.addOverlay('image', id, marker);
                    }
									});

									gMap.addOverlay('image', id, marker);
								});
        }
      	self.myLoading = false;
			},
	//下载土地公告
	downloadFile(url,sn){
		if(url){
			location.href= contextPath + '/boardDetail/3.0/downloadLandNotice?url='+ url + "&sn=" + sn;
		}
	}
  },
  mounted() {
    var self = this;
    //获取板块id对应的板块名称 区县id和区县名称

    this.$nextTick(() => {


    	let _houseParam = {
    	        data: {
    	          cityId: cityId,
    	          cityName: cityName,
    	          boardId: boardId,
    	          propertyType2: "住宅,商业",
    	          pageNum: 1,
    	          pageSize: 5
    	        },
    	        callback: function(response) {
    	          if (response != 50001) {
    	            response.forEach(item => {
    	              if (item.houseType == "新房") {
    	                if (item.propertyType == "住宅") {
    	                  self.newHouseTotal = item.totalNum;
    	                  if(item.boardDetailsHouseDtoList){
    	                  self.getNewHouseResidenceDetails.newHouseResidenceDetails =
    	                    item.boardDetailsHouseDtoList;
    	                  }else{
    	                	  self.getNewHouseResidenceDetails.newHouseResidenceDetails=[];
    	                  }
    	                } else if (item.propertyType == "商铺") {
    	                  self.shopTotal = item.totalNum;
    	                  if(item.boardDetailsHouseDtoList){
    	                  self.getNewHouseShopDetails.newHouseShopDetails =
    	                    item.boardDetailsHouseDtoList;
    	                  }else{
    	                	  self.getNewHouseShopDetails.newHouseShopDetails=[];
    	                  }
    	                } else if (item.propertyType == "写字楼") {
    	                  self.officeTotal = item.totalNum;
    	                  if(item.boardDetailsHouseDtoList){
    	                  self.getNewHouseOfficeDetails.newHouseOfficeDetails =
    	                    item.boardDetailsHouseDtoList;
    	                  }else{
    	                	  self.getNewHouseOfficeDetails.newHouseOfficeDetails=[];
    	                  }
    	                }
    	              } else if (item.houseType == "二手房") {
    	                if (item.propertyType == "住宅") {
    	                  self.secHouseTotal = item.totalNum;
    	                  if(item.boardDetailsHouseDtoList){
    	                    self.getSecondHouseResidenceDetails.secondHouseResidenceDetails =
    	                    item.boardDetailsHouseDtoList;
    	                  }else{
    	                	  self.getSecondHouseResidenceDetails.secondHouseResidenceDetails=[];
    	                  }
    	                } else if (item.propertyType == "商铺") {
    	                  self.shopTotal = item.totalNum;
    	                  if(item.boardDetailsHouseDtoList){
    	                  self.getSecondHouseShopDetails.secondHouseShopDetails =
    	                    item.boardDetailsHouseDtoList;
    	                  }else{
    	                	  self.getSecondHouseShopDetails.secondHouseShopDetails=[];
    	                  }
    	                } else if (item.propertyType == "写字楼") {
    	                  self.officeTotal = item.totalNum;
    	                  if(item.boardDetailsHouseDtoList){
    	                   self.getSecondHouseOfficeDetails.secondHouseOfficeDetails =
    	                    item.boardDetailsHouseDtoList;
    	                  }else{
    	                	  self.getSecondHouseOfficeDetails.secondHouseOfficeDetails=[];
    	                  }
    	                }
    	              }
    	            });
    	          }
    	        }
    	      };
    	   api.requestData.map.getNewHouseResidenceDetails(_houseParam);

    	let _houseParam = {
                data: {
                  cityId: cityId,
                  cityName: cityName,
                  boardId: boardId,
                  propertyType2: "住宅",
                  pageNum: 1,
                  pageSize: 5
                },
                callback: function(response) {
                  if (response != 50001) {
                    response.forEach(item => {
                      if (item.houseType == "新房") {
                        if (item.propertyType == "住宅") {
                          self.newHouseTotal = item.totalNum;
                          if(item.boardDetailsHouseDtoList){
                          self.getNewHouseResidenceDetails.newHouseResidenceDetails =
                            item.boardDetailsHouseDtoList;
                          }else{
                              self.getNewHouseResidenceDetails.newHouseResidenceDetails=[];
                          }
                        }
                      } else if (item.houseType == "二手房") {
                        if (item.propertyType == "住宅") {
                          self.secHouseTotal = item.totalNum;
                          if(item.boardDetailsHouseDtoList){
                            self.getSecondHouseResidenceDetails.secondHouseResidenceDetails =
                            item.boardDetailsHouseDtoList;
                          }else{
                              self.getSecondHouseResidenceDetails.secondHouseResidenceDetails=[];
                          }
                        }
                      }
                    });
                  }
                }
              };
           api.requestData.map.getNewHouseResidenceDetails(_houseParam);

           let _houseParam2 = {
                data: {
                  cityId: cityId,
                  cityName: cityName,
                  boardId: boardId,
                  propertyType2: "商业",
                  pageNum: 1,
                  pageSize: 5
                },
                callback: function(response) {
                  if (response != 50001) {
                    response.forEach(item => {
                      if (item.houseType == "新房") {
                        if (item.propertyType == "商铺") {
                          self.shopTotal = item.totalNum;
                          if(item.boardDetailsHouseDtoList){
                          self.getNewHouseShopDetails.newHouseShopDetails =
                            item.boardDetailsHouseDtoList;
                          }else{
                              self.getNewHouseShopDetails.newHouseShopDetails=[];
                          }
                        }  else if (item.propertyType == "写字楼") {
                          self.officeTotal = item.totalNum;
                          if(item.boardDetailsHouseDtoList){
                          self.getNewHouseOfficeDetails.newHouseOfficeDetails =
                            item.boardDetailsHouseDtoList;
                          }else{
                              self.getNewHouseOfficeDetails.newHouseOfficeDetails=[];
                          }
                        }
                      } else if (item.houseType == "二手房") {
                        if (item.propertyType == "商铺") {
                          self.shopTotal = item.totalNum;
                          if(item.boardDetailsHouseDtoList){
                          self.getSecondHouseShopDetails.secondHouseShopDetails =
                            item.boardDetailsHouseDtoList;
                          }else{
                              self.getSecondHouseShopDetails.secondHouseShopDetails=[];
                          }
                        } else if (item.propertyType == "写字楼") {
                          self.officeTotal = item.totalNum;
                          if(item.boardDetailsHouseDtoList){
                           self.getSecondHouseOfficeDetails.secondHouseOfficeDetails =
                            item.boardDetailsHouseDtoList;
                          }else{
                              self.getSecondHouseOfficeDetails.secondHouseOfficeDetails=[];
                          }
                        }
                      }
                    });
                  }
                }
              };
           api.requestData.map.getNewHouseResidenceDetails(_houseParam2);

      self.$emit("load-complete");
      let getBoardIdAndBoardName = {
        data: {
          cityId: cityId,
          ownerId: "standard",
          type: "板块",
          boardId: boardId
        },
        callback: function(result) {
          let point = [];
          point.push(result[0].centerX);
          point.push(result[0].centerY);
          let bpoint = cityGMap.PointService.Baidu.mercator(point);
          let boardName = result[0].boardName;
          //dom节点更新完成
          map.init(
            function() {
              let gMap = map.gMap;
              //self.maps = [gMap];
              self.$emit('map-complate',[gMap]);
              // map.draw.createMarker(self.centerBuilding)
              // mapConfig.setMap([gMap]);
              //self.choseType(self.changeParam());
              self.getMapData(bpoint,boardName);
            },
            cityName,
            bpoint
          );
        }
      };
      api.requestData.navigation.getBoardIdAndBoardName(getBoardIdAndBoardName);
    });
  }
}
</script>

<style>

</style>


















