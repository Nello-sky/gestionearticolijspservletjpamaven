<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="it.prova.gestionearticolijspservletjpamaven.model.Articolo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Elimina Elemento</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
					  Esempio di operazione fallita!
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
					  Aggiungere d-none nelle class per non far apparire
					   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Elimina elemento</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form method="post" action="ExecuteDeleteArticoloServlet" class="row g-3" novalidate="novalidate">
							
								<% Articolo articoloInPagina = (Articolo)request.getAttribute("ArticoloToDelete"); %>
							
								<div class="col-md-6">
									<label for="codice" class="form-label">Codice <span class="text-danger">*</span></label>
									<input type="text" name="codice" id="codice" class="form-control" placeholder="Inserire il codice"  
										value="<%=articoloInPagina.getCodice()!=null?articoloInPagina.getCodice():"" %>" required>
								</div>
								
								<div class="col-md-6">
									<label for="descrizione" class="form-label">Descrizione <span class="text-danger">*</span></label>
									<input type="text" name="descrizione" id="descrizione" class="form-control" placeholder="Inserire la descrizione"  
										value="<%=articoloInPagina.getDescrizione()!=null?articoloInPagina.getDescrizione():"" %>" required>
								</div>
							
								<div class="col-md-6">
									<label for="prezzo" class="form-label">Prezzo <span class="text-danger">*</span></label>
									<input type="number" class="form-control" name="prezzo" id="prezzo" placeholder="Inserire prezzo" 
									value="<%=articoloInPagina.getPrezzo()!=null?articoloInPagina.getPrezzo():"" %>" required>
								</div>
								
								<div class="col-md-3">
									<label for="dataNascita" class="form-label">Data di Arrivo<span class="text-danger">*</span></label>
									<input class="form-control"  name="dataArrivo" id="dataArrivo" type="date" placeholder="dd/MM/yy" title="formato : gg/mm/aaaa" 
										value="<%=articoloInPagina.getDataArrivo()!=null? articoloInPagina.getDataArrivo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")):""  %>" required/>
								</div>
								
								
									<input type="hidden" class="form-control" name="idDelete" id="idDeleteIDd" 
									value="<%=articoloInPagina.getId()!=null?articoloInPagina.getId():"" %>" required>
								
								
							<div class="col-12">
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Elimina</button>
							</div>
		
						</form>
  
				    <a href="http://localhost:8080/gestionetv/searchForm.jsp" target="_self"> Torna a index</a>
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>