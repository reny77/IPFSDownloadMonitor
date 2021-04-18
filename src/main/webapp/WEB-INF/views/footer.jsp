  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 1.0.0
    </div>
    <strong>Copyright &copy; 2020-2021 <a href="https://www.unipit.it">UNIPI</a>.</strong> All rights reserved.
  </footer>

 
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="<c:url value="/bower_components/jquery/dist/jquery.min.js"/>"></script>
<!-- Bootstrap 3.3.7 -->
<script src="<c:url value="/bower_components/bootstrap/dist/js/bootstrap.min.js"/>"></script>
<!-- SlimScroll -->
<script src="<c:url value="/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"/>"></script>
<!-- FastClick -->
<script src="<c:url value="/bower_components/fastclick/lib/fastclick.js"/>"></script>
<!-- AdminLTE App -->
<script src="<c:url value="/dist/js/adminlte.min.js"/>"></script>
<!-- AdminLTE for demo purposes -->
<script src="<c:url value="/dist/js/demo.js"/>"></script>
<script>
  $(document).ready(function () {
    $('.sidebar-menu').tree()
  })
</script>





<!-- DataTables -->
<script src="<c:url value="/bower_components/datatables.net/js/jquery.dataTables.min.js"/>"></script>
<script src="<c:url value="/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"/>"></script>
<!-- SlimScroll -->
<script src="<c:url value="/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"/>"></script>



<!-- FastClick -->
<script src="<c:url value="/bower_components/fastclick/lib/fastclick.js"/>"></script>
<!-- FLOT CHARTS -->
<script src="<c:url value="/bower_components/Flot/jquery.flot.js"/>"></script>
<!-- FLOT RESIZE PLUGIN - allows the chart to redraw when the window is resized -->
<script src="<c:url value="/bower_components/Flot/jquery.flot.resize.js"/>"></script>
<!-- FLOT PIE PLUGIN - also used to draw donut charts -->
<script src="<c:url value="/bower_components/Flot/jquery.flot.pie.js"/>"></script>
<!-- FLOT CATEGORIES PLUGIN - Used to draw bar charts -->
<script src="<c:url value="/bower_components/Flot/jquery.flot.categories.js"/>"></script>

<!-- Sparkline -->
<script src="<c:url value="/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"/>"></script>
<!-- jvectormap  -->
<script src="<c:url value="/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"/>"></script>
<script src="<c:url value="/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"/>"></script>

<!-- Page script -->
<script>

  /*
   * Custom Label formatter
   * ----------------------
   */
  function labelFormatter(label, series) {
    return '<div style="font-size:13px; text-align:center; padding:2px; color: #fff; font-weight: 600;">'
      + label
      + '<br>'
      + Math.round(series.percent) + '%</div>'
  }
</script>


<script>var context = '<c:url value="/"/>'</script>
<script src="<c:url value="/js/main.js"/>"></script>

</body>
</html>
