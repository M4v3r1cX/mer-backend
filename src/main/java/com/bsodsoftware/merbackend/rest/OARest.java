package com.bsodsoftware.merbackend.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bsodsoftware.merbackend.jpa.entities.Auditoria;
import com.bsodsoftware.merbackend.jpa.entities.Nivel;
import com.bsodsoftware.merbackend.jpa.entities.ObjetivoAprendizaje;
import com.bsodsoftware.merbackend.jpa.entities.SubcategoriaRed;
import com.bsodsoftware.merbackend.services.AuditoriaService;
import com.bsodsoftware.merbackend.services.ObjetivoAprendizajeService;
import com.bsodsoftware.merbackend.services.SecurityService;
import com.bsodsoftware.merbackend.services.to.AsociarOaDTO;
import com.bsodsoftware.merbackend.services.to.OAMerRedes;
import com.bsodsoftware.merbackend.services.to.OAResponse;
import com.bsodsoftware.merbackend.services.to.OaAsociacionDTO;
import com.bsodsoftware.merbackend.services.to.OaDTO;
import com.bsodsoftware.merbackend.services.to.OaHijoDTO;
import com.bsodsoftware.merbackend.services.to.RedResponse;
import com.bsodsoftware.merbackend.services.to.ResponseDTO;

@RestController
@RequestMapping("/oa")
public class OARest {
	
	@Autowired
	private ObjetivoAprendizajeService oaService;
	
	@Autowired
	private SecurityService securityService;
	
	@PostMapping("/save")
	@CrossOrigin
	@ResponseBody
	public ResponseDTO guardarOa(@RequestBody OaDTO oaDto, @RequestHeader("Authorization") String token) {
		ResponseDTO ret = new ResponseDTO();
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				oaService.guardarOa(oaDto, idUsuario);
				ret.setCodigo(200);
			} else {
				ret.setCodigo(500);
				ret.setComentario("Usuario de token no encontrado");
			}
		} catch (Exception ex) {
			ret.setCodigo(500);
			ret.setComentario(ex.getMessage());
			ex.printStackTrace();
		}
		
		return ret;
	}
	
	@PostMapping("/saveAsociarOas")
	@CrossOrigin
	@ResponseBody
	public ResponseDTO guardarAsociarOa(@RequestBody AsociarOaDTO oaDto, @RequestHeader("Authorization") String token) {
		ResponseDTO ret = new ResponseDTO();
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				oaService.guardarOaAsociacion(oaDto, idUsuario);
				ret.setCodigo(200);
			} else {
				ret.setCodigo(500);
				ret.setComentario("Usuario de token no encontrado");
			}
		} catch (Exception ex) {
			ret.setCodigo(500);
			ret.setComentario(ex.getMessage());
			ex.printStackTrace();
		}
		
		return ret;
	}
	
	@GetMapping("getAllOas")
	@CrossOrigin
	@ResponseBody
	public OAResponse getOas(@RequestHeader("Authorization") String token) {
		OAResponse ret = new OAResponse();
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				List<OaDTO> oas = oaService.getOas();
				if (oas != null && !oas.isEmpty()) {
					ret.setCodigo(200);
					ret.setComentario("OAs encontrados");
					ret.setOas(oas);
				} else {
					ret.setCodigo(500);
					ret.setComentario("Actividades no encontradas");
				}
			} else {
				ret.setCodigo(500);
				ret.setComentario("Usuario de token no encontrado");
			}
		} catch (Exception ex) {
			ret.setCodigo(500);
			ret.setComentario(ex.getMessage());
			ex.printStackTrace();
		}
		
		return ret;
	}
	
	@GetMapping("getOAsWeb")
	@CrossOrigin
	@ResponseBody
	public List<OAMerRedes> getOasByCategoria(@RequestHeader("Authorization") String token, @RequestParam Long idCategoria) {
		return oaService.getOasByCategoria(idCategoria);
	}
	
	
	@GetMapping("deleteOa")
	@CrossOrigin
	@ResponseBody
	public ResponseDTO deleteOa(@RequestParam Long id,@RequestHeader("Authorization") String token) {
		ResponseDTO ret = new ResponseDTO();
		
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				oaService.delete(id, idUsuario);
				ret.setCodigo(200);
				ret.setComentario("Eliminado correctamente");
			} else {
				ret.setCodigo(500);
				ret.setComentario("Usuario de token no encontrado");
			}
		} catch (Exception ex) {
			ret.setCodigo(500);
			ret.setComentario(ex.getLocalizedMessage());
			ex.printStackTrace();
		}
		
		return ret;
	}
	
	@GetMapping("getRedes")
	@CrossOrigin
	@ResponseBody
	public RedResponse getRedes(@RequestHeader("Authorization") String token) {
		RedResponse ret = null;
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				ret = oaService.getRedes();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return ret;
	}
	
	@GetMapping("getOa")
	@CrossOrigin
	@ResponseBody
	public OaDTO getOa(@RequestParam Long id,@RequestHeader("Authorization") String token) {
		OaDTO ret = null;
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				ret = oaService.getOa(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	
	@GetMapping("getOaAsociaciones")
	@CrossOrigin
	@ResponseBody
	public OaAsociacionDTO getOaAsociaciones(@RequestParam Long id,@RequestHeader("Authorization") String token) {
		OaAsociacionDTO ret = null;
		
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				ObjetivoAprendizaje oa = oaService.findOaById(id);
				if (oa!= null) {
					ret = new OaAsociacionDTO();
					OaDTO oadto = new OaDTO();
					oadto.setDescripcion(oa.getDescripcion());
					oadto.setId(oa.getId() + "");
					oadto.setCodigo(oa.getNombre());
					oadto.setPrioridad(oa.isPriorizado());
					for (SubcategoriaRed s : oa.getSubcategorias()) {
						oadto.addRed(s.getId() + "");
					}
					for (Nivel n : oa.getNiveles()) {
						oadto.addNivel(n.getId() + "");
					}
					ret.setOaDto(oadto);
					for (ObjetivoAprendizaje oaasociado : oa.getObjetivosAprendizajeUnidos()) {
						OaDTO oaadto = new OaDTO();
						oaadto.setDescripcion(oaasociado.getDescripcion());
						oaadto.setId(oaasociado.getId() + "");
						oaadto.setCodigo(oaasociado.getNombre());
						oaadto.setPrioridad(oaasociado.isPriorizado());
						ret.addOasAsociados(oaadto);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return ret;
	}
	
	@GetMapping("getHijos")
	@CrossOrigin
	@ResponseBody
	public List<OaHijoDTO> getHijos(@RequestParam Long id,@RequestHeader("Authorization") String token) {
		List<OaHijoDTO> ret = null;
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				ret = oaService.getHijos(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
}
