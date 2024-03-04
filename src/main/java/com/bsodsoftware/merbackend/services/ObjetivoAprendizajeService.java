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
import com.bsodsoftware.merbackend.services.to.OAMerRedes;
import com.bsodsoftware.merbackend.services.to.OAMerRedesOA;
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
		oa.setPriorizado(oadto.getPrioridad());	// if editar
		for (String r : oadto.getRedes()) {
			SubcategoriaRed sub = redService.findSubcategoriaById(Long.valueOf(r));
			if (sub != null) {
				if (oa.getSubcategorias() == null || !oa.getSubcategorias().contains(sub)) {
					oa.addSubcategoria(sub);
				}
			}
			
		}
		if (oa.getNiveles() != null && !oa.getNiveles().isEmpty()) {
			oa.getNiveles().removeAll(oa.getNiveles());
			oa = save(oa);
		}
		for (String n : oadto.getNiveles()) {
			Nivel nivel = nivelService.findById(Long.valueOf(n));
			if (nivel != null) {
				if (oa.getNiveles() == null || !oa.getNiveles().contains(nivel)) {
					oa.addNivel(nivel);
				}
			}
		}
		
		if (oadto.getHijosABorrar() != null && !oadto.getHijosABorrar().isEmpty()) {
			for (String s : oadto.getHijosABorrar()) {
				ObjetivoAprendizajeHijo oahijo = findOaHijoById(Long.valueOf(s));
				oa.getHijos().remove(oahijo);
				save(oa);
				oahijo.setObjetivoAprendizaje(null);
				save(oahijo);
				oaHijoRepository.delete(oahijo);
			}
		}
		
		if (oadto.getHijos() != null && !oadto.getHijos().isEmpty()) {
			for (OaHijoDTO oahijodto : oadto.getHijos()) {
				boolean nuevo = false;
				boolean hijoPorDefecto = false;
				ObjetivoAprendizajeHijo oahijo = null;
				if (oahijodto.getId() != null) {
					oahijo = findOaHijoById(oahijodto.getId());
					hijoPorDefecto = true;
				} else {
					oahijo = new ObjetivoAprendizajeHijo();
					nuevo = true;
				}
				oahijo.setDescripcion(oahijodto.getDescripcion());
				oahijo.setObjetivoAprendizaje(oa);
				oahijo.setPriorizado(oahijodto.getPrioridad());
				oahijo.setHijoPorDefecto(hijoPorDefecto);
				if (!nuevo) {
					oahijo.getSubcategorias().removeAll(oahijo.getSubcategorias());
					oaHijoRepository.saveAndFlush(oahijo);
				}
				for (String r : oahijodto.getRedes()) {
					SubcategoriaRed sub = redService.findSubcategoriaById(Long.valueOf(r));
					if (sub != null) {
						if (oahijo.getSubcategorias() == null || !oahijo.getSubcategorias().contains(sub)) {
							oahijo.addSubcategoria(sub);
						}
					}
				}
				if (nuevo) {
					save(oa);
					oahijo.setObjetivoAprendizaje(oa);
					save(oahijo);
					oa.addHijo(oahijo);
					save(oa);
				} else {
					save(oahijo);
				}
				
			}
		} else {
			ObjetivoAprendizajeHijo oahijo = new ObjetivoAprendizajeHijo();
			oahijo.setHijoPorDefecto(true);
			oahijo.setDescripcion(oadto.getDescripcion());
			oahijo.setObjetivoAprendizaje(oa);
			oahijo.setPriorizado(oadto.getPrioridad());
			for (String r : oadto.getRedes()) {
				SubcategoriaRed sub = redService.findSubcategoriaById(Long.valueOf(r));
				if (oahijo.getSubcategorias() == null || sub != null) {
					oahijo.addSubcategoria(sub);
				}
			}
			for (String n : oadto.getNiveles()) {
				Nivel nivel = nivelService.findById(Long.valueOf(n));
				if (nivel != null) {
					oahijo.addNivel(nivel);
				}
			}
			save(oa);
			oahijo.setObjetivoAprendizaje(oa);
			save(oahijo);
			oa.addHijo(oahijo);
			save(oa);
		}
		//oa = save(oa);
		//oaHijoRepository.saveAll(oa.getHijos());
		auditoriaService.guardarAccion(accion, idUsuario, oa.getId());
	}
	
	public void guardarOaAsociacion(AsociarOaDTO dto, Long idUsuario) {
		ObjetivoAprendizaje oa1 = findOaById(Long.valueOf(dto.getIdOaInicial()));
		if (oa1 != null) {
			if (oa1.getObjetivosAprendizajeUnidos() != null && !oa1.getObjetivosAprendizajeUnidos().isEmpty()) {
				oa1.getObjetivosAprendizajeUnidos().removeAll(oa1.getObjetivosAprendizajeUnidos());
				save(oa1);
			}
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
					oahijo.getNiveles().removeAll(oahijo.getNiveles());
					save(oahijo);
					oahijo.getSubcategorias().removeAll(oahijo.getSubcategorias());
					save(oahijo);
					
					oaHijoRepository.delete(oahijo);
				}
				oa.getHijos().removeAll(oa.getHijos());
				save(oa);
			}
			oa.getNiveles().removeAll(oa.getNiveles());
			save(oa);
			oa.getSubcategorias().removeAll(oa.getSubcategorias());
			save(oa);
			oa.getObjetivosAprendizajeUnidos().removeAll(oa.getObjetivosAprendizajeUnidos());
			save(oa);
			
			oaRepository.delete(oa);
			auditoriaService.guardarAccion(Auditoria.ACCION.OA_ELIMINAR, idUsuario, id);
		}
		
	}
	
	public List<OaDTO> getOas() {
		List<OaDTO> ret = null;
		List<ObjetivoAprendizaje> oas = oaRepository.findAll();
		if (oas != null && !oas.isEmpty()) {
			ret = new ArrayList<OaDTO>();
			for (ObjetivoAprendizaje oa : oas) {
				OaDTO oadto = new OaDTO();
				oadto.setId(oa.getId() + "");
				for (ObjetivoAprendizajeHijo oahijo : oa.getHijos()) {
					for (SubcategoriaRed s : oahijo.getSubcategorias()) {
						Red r = s.getRed();
						if (oadto.getRedes() == null || !oadto.getRedes().contains(r.getNombre())) {
							oadto.addRed(r.getNombre());
						}
					}
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
	
	public List<OAMerRedes> getOasByCategoria(Long idCategoria) {
		List<OAMerRedes> ret = new ArrayList<OAMerRedes>();
		// Se hace de a uno pa poder mandar la wea ordenada al front y no dar jugo
		List<ObjetivoAprendizaje> oas = oaRepository.findOasByNivelAndRed(1L, idCategoria);
		OAMerRedes oam = new OAMerRedes();
		oam.setNivel(1L);
		if (oas != null && !oas.isEmpty()) {
			for (ObjetivoAprendizaje oa : oas) {
				OAMerRedesOA oamroa = new OAMerRedesOA();
				oamroa.setId(oa.getId());
				oamroa.setDescripcion(oa.getDescripcion());
				oamroa.setNombre(oa.getNombre());
				oam.addOa(oamroa);
			}
		} else {
			OAMerRedesOA oamroa = new OAMerRedesOA();
			oamroa.setId(0L);
			oamroa.setDescripcion("Nivel no cuenta con OAs registrados");
			oamroa.setNombre("");
			oam.addOa(oamroa);
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
	
	public List<OaTmDto> getOasTmsFiltrados(String nivel, String red) {
		List<OaTmDto> ret = null;
		List<ObjetivoAprendizajeHijo> oas = oaHijoRepository.findAllFiltrados(Long.valueOf(nivel), Long.valueOf(red));
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
			ret.setHijosABorrar(new ArrayList<String>());
			ret.setDescripcion(oa.getDescripcion());
			ret.setId(oa.getId() + "");
			ret.setCodigo(oa.getNombre());
			ret.setPrioridad(oa.isPriorizado());
			for (SubcategoriaRed s : oa.getSubcategorias()) {
				ret.addRed(s.getId() + "");
			}
			for (Nivel n : oa.getNiveles()) {
				ret.addNivel(n.getId() + "");
			}
			for (ObjetivoAprendizajeHijo oah : oa.getHijos()) {
				OaHijoDTO otd = new OaHijoDTO();
				otd.setId(oah.getId());
				otd.setDescripcion(oah.getDescripcion());
				otd.setPrioridad(oah.isPriorizado());
				for (SubcategoriaRed r : oah.getSubcategorias()) {
					otd.addRed(r.getId() + "");
				}
				for (Nivel n : oah.getNiveles()) {
					otd.addNivel(n.getNombre());
				}
				ret.addHijo(otd);
			}
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
				for (Nivel n : oa.getNiveles()) {
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
	
	public List<ObjetivoAprendizaje> findObjetivosByRed(Long id) {
		
	}
}
