<%@ include file = "header.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header text-center">
      <h2>Insert a CID and start download</h2>
    </section>

    <!-- Main content -->
    <section class="content text-center">
		<div class="row">
	        <!-- left column -->
			<div class="col-md-3"></div>
			<div class="col-md-6">
			          <!-- general form elements -->
			    
			      <!-- Default box -->
			      <div class="box box-primary">
			        <div class="box-header with-border center">
			          <h3 class="box-title text-center">Insert a CID and start Download</h3>
			        </div>
			        <form class="form-horizontal" action="<c:url value="/download.htm"/>" method="get">
				        <div class="box-body">
				         	<div class="has-error input-group input-group-lg">
				                <input type="text" class="form-control" name="cid" value="QmSnuWmxptJZdLJpKRarxBMS2Ju2oANVrgbr2xWbie9b2D">
				                <span class="input-group-btn">
				                	<button type="submit" class="btn btn-info btn-flat">Download</button>
				                </span>
				            </div>
				            <div class="has-error input-group input-group-lg">
				            	<span class="help-block">The cid inserted is not correct</span>
				            </div>
				        </div>
			        </form>
			        <!-- /.box-footer--> 
			      </div>
			      <!-- /.box -->
			</div>
			<div class="col-md-3"></div>
		</div>
    </section>
    <!-- /.content -->
  </div>
  
<%@ include file = "footer.jsp" %>
