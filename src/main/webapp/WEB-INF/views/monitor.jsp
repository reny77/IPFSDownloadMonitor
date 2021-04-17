<%@ include file = "header.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header text-center">
      <h2>Download monitor</h2>
    </section>

    <!-- Main content -->
    <section class="content">
    
    <div class="row">
    	<div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-red"><i class="fa fa-cloud-download"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Download size</span>
              <span id="download-size" class="info-box-number 	sizeHumanReadbleobjectinfo">-</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
    
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-yellow"><i class="fa fa-download"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Downloaded</span>
              <span class="info-box-number recivedTotaldownloadstats">-</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
    
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-aqua"><i class="fa fa-users"></i></span>
            <div class="info-box-content">
              <span class="info-box-text">Peers</span>
              <span class="info-box-number peersConnecteddownloadstats">-</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        

        <!-- fix for small devices only -->
        <div class="clearfix visible-sm-block"></div>

        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-green"><i class="fa fa-smile-o"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Contributors</span>
              <span class="info-box-number contributordownloadstats">-</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        
      </div>
      
      
      
      <%-- @ include file = "map.jsp" --%>
      
      
      <div class="row">
      	<div class="col-xs-4">
          <div class="box box-solid">
            <div class="box-header with-border">
              <i class="fa  fa-info-circle"></i>

              <h3 class="box-title">Download details</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <dl class="dl-horizontal">
                <dt>CID</dt>
                <dd class="cidobjectinfo">-</dd>
                
                <dt>Hash</dt>
                <dd class="hashobjectinfo">-</dd>
                
                <dt>Download start date</dt>
                <dd class="startDateobjectinfo">-</dd>

                <dt>Type</dt>
                <dd class="typeobjectinfo">-</dd>
                
                <dt>Size</dt>
                <dd class="sizeHumanReadbleobjectinfo">-</dd>
                
              </dl>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        
        
        
      
       <div class="col-xs-8">
          <!-- interactive chart -->
          <div class="box box-primary">
            <div class="box-header with-border">
              <i class="fa fa-bar-chart-o"></i>
              <h3 class="box-title">Interactive Area Chart</h3>
            </div>
            <div class="box-body">
              <div id="interactive" style="height: 300px;"></div>
            </div>
            <!-- /.box-body-->
          </div>
          <!-- /.box -->

        </div>
        <!-- /.col -->
        
        
        
        <!-- /.col -->
      </div>
      
      <div class="row">
      	<div class="col-xs-8">
      	
	      	<div class="box">
            <div class="box-header">
              <h3 class="box-title">Contributors table</h3>
            </div>
            <!-- /.box-header -->
	            <div class="box-body">
	            	<table id="example2" class="table table-bordered table-striped" style="width:100%">
	            		<!-- <img src="https://www.countryflags.io/be/flat/16.png">	 -->
        				<thead>
				            <tr>
				                <th>Country Code</th>
				                <th>Country</th>
				                <th>City</th>
				            	<th>Ip</th>
				                <th>Peer</th>
				                <th>Recv</th>
				                <th>Sent</th>
				                <th>Exchanged</th>
				            </tr>
				        </thead>
				        <tfoot>
				            <tr>
								<th>Country Code</th>
				                <th>Country</th>
				                <th>City</th>
				            	<th>Ip</th>
				                <th>Peer</th>
				                <th>Recv</th>
				                <th>Sent</th>
				                <th>Exchanged</th>
				            </tr>
				        </tfoot>
				    </table>
	            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
	      	</div>
	      	<div class="col-xs-4">
	      	  <!-- Donut chart -->
          <div class="box box-primary">
            <div class="box-header with-border">
              <i class="fa fa-bar-chart-o"></i>
              <h3 class="box-title">Contributors pie</h3>
            </div>
            <div class="box-body">
              <div id="donut-chart" style="height: 300px;"></div>
            </div>
            <!-- /.box-body-->
          </div>
          <!-- /.box -->
      
      	</div>
      </div>
    
    </section>
    <!-- /.content -->
  </div>
  
  
<%@ include file = "footer.jsp" %>
