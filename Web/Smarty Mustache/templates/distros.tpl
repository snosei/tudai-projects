<div class="table-responsive">
	<table class="table table-condensed">
		<thead>
			<tr>
				<td>Nombre</td>
				<td>Arquitectura</td>
				<td>Req. Memoria</td>
				<td>Req. Disco</td>
				<td>Descarga</td>
			</tr>
		</thead>
		<tbody>
			{foreach $distros as $distro}
				<tr>
					<td>
						<a href='{$distro["link_distro"]}' target='_blank' >
							<img class='Logos' src='{$distro["logo"]}' alt='{$distro["nombre"]}' />
						</a>
						<span>{$distro["nombre"]}</span>
					</td>
					<td>{$distro["arquitectura"]}</td>
					<td>{$distro["requisito_memoria"]}</td>
					<td>{$distro["requisito_disco"]}</td>
					<td>
						<a href='{$distro["link_descarga"]}' target='_blank' >
							<img class='Logos' src="images/logomega.png" alt='Link Mega' /></a>
						</td>
				</tr>
			{/foreach}
		</tbody>
	</table>
</div>
<p>*Los requisitos m&iacute;nimos de memoria pueden variar en cada distribuci&oacute;n dependiendo de las aplicaciones instaladas.</p>
