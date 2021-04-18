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
				         	<div class="input-group input-group-lg <c:if test="${errorcid}">has-error</c:if>">
				                <input type="text" class="form-control" name="cid" value="QmSnuWmxptJZdLJpKRarxBMS2Ju2oANVrgbr2xWbie9b2D">
				                <span class="input-group-btn">
				                	<button type="submit" class="btn btn-info btn-flat">Download</button>
				                </span>
				            </div>
				            <div class="has-error input-group input-group-lg">
				            <c:if test="${errorcid}"><span class="help-block">The cid inserted is not correct</span></c:if>
				            </div>
				            <br>
				            <div class="row">
				        		<div class="col-xs-5">
				                  <div class="form-group">
					                  <label for="inHost" class="col-sm-2 control-label">host</label>
					                  <div class="col-sm-10">
					                    <input type="text" class="form-control" id="inHost" name="host" value="localhost">
					                  </div>
					                </div>
				                </div>
				                <div class="col-xs-3">
				                  <div class="form-group">
					                  <label for="inPort" class="col-sm-2 control-label">port</label>
					                  <div class="col-sm-10">
					                    <input type="text" class="form-control" id="inPort" name="port" value="5001">
					                  </div>
					                </div>
				                </div>
				              </div>
				        </div>
			        </form>
			        <!-- /.box-footer--> 
			        <c:if test="${erroripfs}">
				        <div class="alert alert-danger alert-dismissible">
			                <h4><i class="icon fa fa-ban"></i> IPFS problem!</h4>
			                Is IPFS daemon running?
		              </div>
		            </c:if>
			      </div>
			      <!-- /.box -->
			</div>
		</div>
    </section>
    <!-- /.content -->
  </div>
  
<%@ include file = "footer.jsp" %>
