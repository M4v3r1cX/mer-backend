package com.bsodsoftware.merbackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.Auditoria;
import com.bsodsoftware.merbackend.jpa.entities.Nivel;
import com.bsodsoftware.merbackend.jpa.entities.ObjetivoAprendizaje;
import com.bsodsoftware.merbackend.jpa.entities.ObjetivoAprendizajeHijo;
import com.bsodsoftware.merbackend.jpa.entities.Red;
import com.bsodsoftware.merbackend.jpa.entities.SubcategoriaRed;
import com.bsodsoftware.merbackend.jpa.repository.ObjetivoAprendizajeHijoRepository;
import com.bsodsoftware.merbackend.jpa.repository.ObjetivoAprendizajeRepository;
import com.bsodsoftware.merbackend.services.to.AsociarOaDTO;
import com.bsodsoftware.merbackend.services.to.LibroRedDTO;
import com.bsodsoftware.merbackend.services.to.OaDTO;
import com.bsodsoftware.merbackend.services.to.OaHijoDTO;
import com.bsodsoftware.merbackend.services.to.OaTmDto;
import com.bsodsoftware.merbackend.services.to.RedResponse;
import com.bsodsoftware.merbackend.services.to.SubcategoriaDTO;

@Service
public class ObjetivoAprendizajeService {
	
	@Autowired
	private RedService redService;
	
	@Autowired
	private NivelService nivelService;

	@Autowired
	private ObjetivoAprendizajeRepository oaRepository;
	
	@Autowired
	private ObjetivoAprendizajeHijoRepository oaHijoRepository;
	
	@Autowired
	private AuditoriaService auditoriaService;
	
	public ObjetivoAprendizaje save(ObjetivoAprendizaje entity) {
		return oaRepository.saveAndFlush(entity);
	}
	
	public ObjetivoAprendizajeHijo save(ObjetivoAprendizajeHijo entity) {
		return oaHijoRepository.saveAndFlush(entity);
	}
	
	public void guardarOa(OaDTO oadto, Long idUsuario) {
		Auditoria.ACCION accion = null;
		ObjetivoAprendizaje oa = null;
		if (oadto.getId() != null && !oadto.getId().isEmpty()) {
			oa = oaRepository.getReferenceById(Long.valueOf(oadto.getId()));
			accion = Auditoria.ACCION.OA_EDITAR;
		} else {
			oa = new ObjetivoAprendizaje();
			accion = Auditoria.ACCION.OA_CREAR;
		}
		oa.setDescripcion(oadto.getDescripcion());
		oa.setIdUsuario(idUsuario);
		oa.setNombre(oadto.getCodigo());
		oa.setPriorizado(oadto.getPrioridad());
		for (String r : oadto.getRedes()) {
			SubcategoriaRed sub = redService.findSubcategoriaById(Long.valueOf(r));
			if (sub != null) {
				oa.addSubcategoria(sub);
			}
			/*Red red = redService.findById(Long.valueOf(r));
			if (red != null) {
				oa.addRed(red);
			}*/
			
		}
		for (String n : oadto.getNiveles()) {
			Nivel nivel = nivelService.findById(Long.valueOf(n));
			if (nivel != null) {
				oa.addNivel(nivel);
			}
		}
		
		if (oadto.getHijos() != null && !oadto.getHijos().isEmpty()) {
			for (OaHijoDTO oahijodto : oadto.getHijos()) {
				ObjetivoAprendizajeHijo oahijo = new ObjetivoAprendizajeHijo();
				oahijo.setDescripcion(oahijodto.getDescripcion());
				oahijo.setObjetivoAprendizaje(oa);
				oahijo.setPriorizado(oahijodto.getPrioridad());
				oahijo.setHijoPorDefecto(false);
				for (String r : oahijodto.getRedes()) {
					SubcategoriaRed sub = redService.findSubcategoriaById(Long.valueOf(r));
					if (sub != null) {
						oahijo.addSubcategoria(sub);
					}
				}
				for (String n : oahijodto.getNiveles()) {
					Nivel nivel = nivelService.findById(Long.valueOf(n));
					if (nivel != null) {
						oahijo.addNivel(nivel);
					}
				}
				oa.addHijo(oahijo);
			}
		} else {
			ObjetivoAprendizajeHijo oahijo = new ObjetivoAprendizajeHijo();
			oahijo.setHijoPorDefecto(true);
			oahijo.setDescripcion(oadto.getDescripcion());
			oahijo.setObjetivoAprendizaje(oa);
			oahijo.setPriorizado(oadto.getPrioridad());
			for (String r : oadto.getRedes()) {
				SubcategoriaRed sub = redService.findSubcategoriaById(Long.valueOf(r));
				if (sub != null) {
					oahijo.addSubcategoria(sub);
				}
			}
			for (String n : oadto.getNiveles()) {
				Nivel nivel = nivelService.findById(Long.valueOf(n));
				if (nivel != null) {
					oahijo.addNivel(nivel);
				}
			}
			oa.addHijo(oahijo);
		}
		oa = save(oa);
		oaHijoRepository.saveAll(oa.getHijos());
		auditoriaService.guardarAccion(accion, idUsuario, oa.getId());
	}
	
	public void guardarOaAsociacion(AsociarOaDTO dto, Long idUsuario) {
		ObjetivoAprendizaje oa1 = findOaById(Long.valueOf(dto.getIdOaInicial()));
		if (oa1 != null) {
			for (String id : dto.getIdOasFinales()) {
				ObjetivoAprendizaje oa2 = findOaById(Long.valueOf(id));
				if (oa2 != null) {
					oa1.addObjetivoAprendizajeUnido(oa2);
				}
			}
			save(oa1);
			auditoriaService.guardarAccion(Auditoria.ACCION.ASOCIACION_GUARDAR, idUsuario, oa1.getId());
		}
	}
	
	public void delete(Long id, Long idUsuario) {
		ObjetivoAprendizaje oa = oaRepository.getReferenceById(id);
		if (oa != null) {
			if (oa.getHijos() != null && !oa.getHijos().isEmpty()) {
				for (ObjetivoAprendizajeHijo oahijo : oa.getHijos()) {
					oaHijoRepository.delete(oahijo);
				}
			}
		}
		oaRepository.deleteById(id);
		auditoriaService.guardarAccion(Auditoria.ACCION.OA_ELIMINAR, idUsuario, id);
	}
	
	public List<OaDTO> getOas() {
		List<OaDTO> ret = null;
		List<ObjetivoAprendizaje> oas = oaRepository.findAll();
		if (oas != null && !oas.isEmpty()) {
			ret = new ArrayList<OaDTO>();
			for (ObjetivoAprendizaje oa : oas) {
				OaDTO oadto = new OaDTO();
				oadto.setId(oa.getId() + "");
				/*for (Red r : oa.getRedes()) {
					oadto.addRed(r.getNombre());
				}*/
				for (SubcategoriaRed s : oa.getSubcategorias()) {
					Red r = s.getRed();
					oadto.addRed(r.getNombre());
				}
				for (Nivel n : oa.getNiveles()) {
					oadto.addNivel(n.getNombre());
				}
				oadto.setCodigo(oa.getNombre());
				oadto.setDescripcion(oa.getDescripcion());
				oadto.setPrioridad(oa.isPriorizado());
				ret.add(oadto);
			}
		}
		
		return ret;
	}
	
	public List<OaTmDto> getOasTms() {
		List<OaTmDto> ret = null;
		List<ObjetivoAprendizajeHijo> oas = oaHijoRepository.findAll();
		if (oas != null && !oas.isEmpty()) {
			ret = new ArrayList<OaTmDto>();
			for (ObjetivoAprendizajeHijo oa : oas) {
				OaTmDto otd = new OaTmDto();
				otd.setId(oa.getId() + "");
				otd.setCodigo(oa.getObjetivoAprendizaje().getNombre());
				otd.setDescripcion(oa.getDescripcion());
				ret.add(otd);
			}
		}
		return ret;
	}
	
	public OaDTO getOa(Long id) {
		OaDTO ret = null;
		ObjetivoAprendizaje oa = oaRepository.getReferenceById(id);
		if (oa != null) {
			ret = new OaDTO();
			ret.setDescripcion(oa.getDescripcion());
			ret.setId(oa.getId() + "");
			ret.setCodigo(oa.getNombre());
			ret.setPrioridad(oa.isPriorizado());
			/*for (Red r : oa.getRedes()) {
				ret.addRed(r.getId() + "");
			}*/
			for (SubcategoriaRed s : oa.getSubcategorias()) {
				//Red r = s.getRed();
				ret.addRed(s.getId() + "");
			}
			for (Nivel n : oa.getNiveles()) {
				ret.addNivel(n.getId() + "");
			}
			/*for (ObjetivoAprendizajeHijo oah : oa.getHijos()) {
				OaHijoDTO otd = new OaHijoDTO();
				otd.setId(oah.getId());
				otd.setDescripcion(oah.getDescripcion());
				otd.setPrioridad(oah.isPriorizado());
				for (SubcategoriaRed r : oah.getSubcategorias()) {
					otd.addRed(r.getRed().getNombre());
				}
				for (Nivel n : oah.getNiveles()) {
					otd.addNivel(n.getNombre());
				}
				ret.addHijo(otd);
			}*/
		}
		return ret;
	}
	
	public ObjetivoAprendizaje findOaById(Long id) {
		ObjetivoAprendizaje oa = null;
		try {
			Optional<ObjetivoAprendizaje> op = oaRepository.findById(id);
			oa = op.get();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oa;
	}
	
	public ObjetivoAprendizajeHijo findOaHijoById(Long id) {
		ObjetivoAprendizajeHijo oaHijo = null;
		try {
			Optional<ObjetivoAprendizajeHijo> op = oaHijoRepository.findById(id);
			oaHijo = op.get();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oaHijo;
	}
	
	public List<OaHijoDTO> getHijos(Long id) {
		List<OaHijoDTO> ret = null;
		
		ObjetivoAprendizaje oa = oaRepository.getReferenceById(id);
		if (oa != null && oa.getHijos() != null && !oa.getHijos().isEmpty()) {
			ret = new ArrayList<OaHijoDTO>();
			for (ObjetivoAprendizajeHijo oahijo : oa.getHijos()) {
				OaHijoDTO oadto = new OaHijoDTO();
				oadto.setDescripcion(oahijo.getDescripcion());
				oadto.setPrioridad(oahijo.isPriorizado() != null ? oahijo.isPriorizado() : false);
				for (SubcategoriaRed r : oahijo.getSubcategorias()) {
					oadto.addRed(r.getRed().getNombre());
				}
				for (Nivel n : oahijo.getNiveles()) {
					oadto.addNivel(n.getNombre());
				}
				ret.add(oadto);
			}
		}
		
		return ret;
	}
	
	public RedResponse getRedes() {
		RedResponse ret = null;
		List<LibroRedDTO> reds = null;
		List<Red> redes = redService.findAll();
		if (redes != null && !redes.isEmpty()) {
			reds = new ArrayList<LibroRedDTO>();
			for (Red r : redes) {
				LibroRedDTO l = new LibroRedDTO();
				l.setId(r.getId());
				l.setNombre(r.getNombre());
				if (r.getSubcategoriaRed() != null && !r.getSubcategoriaRed().isEmpty()) {
					for (SubcategoriaRed s : r.getSubcategoriaRed()) {
						SubcategoriaDTO sdto = new SubcategoriaDTO();
						sdto.setId(s.getId());
						sdto.setDescripcion(s.getDescripcion());
						l.addSubcategoria(sdto);
					}
				}
				reds.add(l);
			}
		}
		
		if (reds != null) {
			ret = new RedResponse();
			ret.setRedes(reds);
		}
		
		return ret;
	}
}
