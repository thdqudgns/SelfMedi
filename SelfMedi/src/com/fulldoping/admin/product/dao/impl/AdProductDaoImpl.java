package com.fulldoping.admin.product.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.fulldoping.admin.product.dao.face.AdProductDao;
import com.fulldoping.admin.product.paging.AdProductPaging;
import com.fulldoping.common.JDBCTemplate;
import com.fulldoping.product.dto.NutrientInfo;
import com.fulldoping.product.dto.NutrientKind;
import com.fulldoping.product.dto.ProductInfo;
import com.fulldoping.product.dto.SymptomCode;
import com.fulldoping.product.dto.SymptomInfo;
import com.fulldoping.product.dto.TargetCode;
import com.fulldoping.product.dto.TargetInfo;




public class AdProductDaoImpl implements AdProductDao{

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체

	@Override
	public int selectCntAll(Connection conn) {
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM productinfo";

		//총 상품 수
		int count = 0;

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()) {
				count = rs.getInt(1);//첫번째 컬럼의 정보를 얻어 온다.
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return count;
	}

	@Override
	public List<ProductInfo> selectAllProduct(Connection conn, AdProductPaging paging) {

		//SQL작성
		String sql = "";
		sql += "SELECT * FROM(";
		sql += " 	SELECT rownum rnum, p.* FROM";
		sql += " 		(SELECT * FROM PRODUCTINFO";
		sql += " 		ORDER BY starscore DESC";
		sql += " 		)p";
		sql += " 	)product";
		sql += " WHERE rnum BETWEEN ? AND ?";

		//결과 저장할 List 객체 생성
		List<ProductInfo> productList = new ArrayList<>(); //Board = dto boardList = 객체이름, 반환객체

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery();

			while(rs.next()) {
				ProductInfo productInfo = new ProductInfo();

				productInfo.setProductId( rs.getLong("productId") );
				productInfo.setProductName( rs.getString("productName") );
				productInfo.setManuCom( rs.getString("manuCom") );
				productInfo.setType( rs.getString("type") );
				productInfo.setImage( rs.getString("image") );
				productInfo.setPurchaseLink( rs.getString("purchaseLink") );
				productInfo.setAllergyInfo( rs.getString("allergyInfo") );
				productInfo.setStarScore( rs.getString("starScore") );

				//리스트에 결과값 저장
				productList.add(productInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return productList;
	}

	@Override
	public List<TargetCode> selectAllTargetCode(Connection conn) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM targetCode";
		sql += " ORDER BY targetId ASC";

		List<TargetCode> targetCode = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장

			//조회 결과 처리
			while(rs.next()) {
				TargetCode t = new TargetCode(); //결과값 저장 객체

				//결과값 한 행 처리
				t.setTargetId( rs.getInt("targetId"));
				t.setTargetName( rs.getString("targetName"));

				//리스트에 결과값 저장
				targetCode.add(t);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		//최종 결과 반환
		return targetCode;
	}

	@Override
	public List<SymptomCode> selectAllSymptomCode(Connection conn) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM symptomCode";
		sql += " ORDER BY symptomId ASC";

		List<SymptomCode> symptomCode = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장

			//조회 결과 처리
			while(rs.next()) {
				SymptomCode s = new SymptomCode(); //결과값 저장 객체

				//결과값 한 행 처리
				s.setSymptomId(rs.getInt("symptomId"));
				s.setSymptomName( rs.getString("symptomName"));

				//리스트에 결과값 저장
				symptomCode.add(s);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		//최종 결과 반환
		return symptomCode;
	}

	@Override
	public List<NutrientKind> selectAllNutrientKind(Connection conn) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM nutrientKind";
		sql += " ORDER BY nutId ASC";

		List<NutrientKind> nutrientKind = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장

			//조회 결과 처리
			while(rs.next()) {
				NutrientKind n = new NutrientKind(); //결과값 저장 객체

				//결과값 한 행 처리
				n.setNutId( rs.getInt("nutId"));
				n.setNutKind( rs.getString("nutKind"));
				n.setEffect( rs.getString("effect"));
				n.setDeficiency( rs.getString("deficiency"));
				n.setHyperact( rs.getString("hyperact"));
				n.setRcmYth( rs.getString("rcmYth"));
				n.setRcmAdult( rs.getString("rcmAdult"));
				n.setRcmSen( rs.getString("rcmSen"));

				//리스트에 결과값 저장
				nutrientKind.add(n);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		//최종 결과 반환
		return nutrientKind;
	}

	@Override
	public int insertProductInfo(Connection conn, ProductInfo productInfo) {
		//SQL

		String sql = "";
		sql += "INSERT INTO productInfo";
		sql += " VALUES(?, ?, ?, ?, ?, ?, ?, 0)";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, productInfo.getProductId());
			ps.setString(2, productInfo.getProductName());
			ps.setString(3, productInfo.getManuCom());
			ps.setString(4, productInfo.getType());
			ps.setString(5, productInfo.getImage());
			ps.setString(6, productInfo.getPurchaseLink());
			ps.setString(7, productInfo.getAllergyInfo());

			res = ps.executeUpdate();



		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(ps);
		}
		return res;
	}

	@Override
	public int insertTargetInfo(Connection conn, TargetInfo targetInfo) {
		//SQL

		String sql = "";
		sql += "INSERT INTO targetInfo";
		sql += " VALUES( ?, ? )";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setLong(1, targetInfo.getProductId());
			ps.setInt(2, targetInfo.getTargetId());

			res = ps.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(ps);
		}
		return res;
	}

	@Override
	public int insertSymptomInfo(Connection conn, List<SymptomInfo> symptomInfo) {
		//SQL 작성
		String sql = "";
		sql += "INSERT INTO symptominfo";
		sql += " VALUES(?, ?)";

		int res = 0;

		for(int i = 0; i<symptomInfo.size(); i++) {
			try {

				ps = conn.prepareStatement(sql); //SQL수행 객체
				ps.setLong(1, symptomInfo.get(i).getProductId());
				ps.setInt(2, symptomInfo.get(i).getSymptomId());

				res = ps.executeUpdate(); //SQL 수행 및 결과집합 저장


			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				//DB객체 닫기
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}
		}
		//최종 결과 반환
		return res;
	}

	@Override
	public int insertNutrientInfo(Connection conn, List<NutrientInfo> nutrientInfo) {
		//SQL 작성
				String sql = "";
				sql += "INSERT INTO nutrientInfo";
				sql += " VALUES(?, ?, ?)";

				int res = 0;

				for(int i = 0; i<nutrientInfo.size(); i++) {
					try {

						ps = conn.prepareStatement(sql); //SQL수행 객체
						ps.setLong(1, nutrientInfo.get(i).getProductId());
						ps.setInt(2, nutrientInfo.get(i).getNutId());
						ps.setString(3, nutrientInfo.get(i).getNutContent());

						res = ps.executeUpdate(); //SQL 수행 및 결과집합 저장


					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						//DB객체 닫기
						JDBCTemplate.close(rs);
						JDBCTemplate.close(ps);
					}
				}
				//최종 결과 반환
				return res;
	}

	@Override
	public ProductInfo selectProductInfo(Connection conn, long productId) {
		String sql = "";
		sql += "SELECT * FROM ProductInfo";
		sql += " WHERE productid = ?";
		
		ProductInfo productInfo = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, productId);

			rs = ps.executeQuery();

			rs.next();
			
				productInfo = new ProductInfo();

				productInfo.setProductId( rs.getLong("productId") );
				productInfo.setProductName( rs.getString("productName") );
				productInfo.setManuCom( rs.getString("manuCom") );
				productInfo.setType( rs.getString("type") );
				productInfo.setImage( rs.getString("image") );
				productInfo.setPurchaseLink( rs.getString("purchaseLink") );
				productInfo.setAllergyInfo( rs.getString("allergyInfo") );
				productInfo.setStarScore( rs.getString("starScore") );

				//리스트에 결과값 저장

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return productInfo;
	}

	@Override
	public int selectTargetInfo(Connection conn, long productId) {
		String sql = "";
		sql += "SELECT * FROM TargetInfo";
		sql += " WHERE productid = ?";
		
		int targetId = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, productId);

			rs = ps.executeQuery();

			while(rs.next()) {
				
				targetId =rs.getInt("targetId");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return targetId;
	}

	@Override
	public List<SymptomInfo> selectSymtomInfo(Connection conn, long productId) {
		
		String sql = "";
		sql += "SELECT * FROM SymptomInfo";
		sql += " WHERE productid = ?";
		
		List<SymptomInfo> symptomList = new ArrayList<>(); //Board = dto boardList = 객체이름, 반환객체

		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, productId);

			rs = ps.executeQuery();

			while(rs.next()) {
				SymptomInfo symptomInfo = new SymptomInfo();

				symptomInfo.setSymptomId( rs.getInt("symptomId") );

				//리스트에 결과값 저장
				symptomList.add(symptomInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return symptomList;
	}

	@Override
	public List<NutrientInfo> selectNutrientInfo(Connection conn, long productId) {
		
		String sql = "";
		sql += "SELECT * FROM NutrientInfo";
		sql += " WHERE productid = ?";
		
		List<NutrientInfo> symptomList = new ArrayList<>(); //Board = dto boardList = 객체이름, 반환객체

		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, productId);

			rs = ps.executeQuery();

			while(rs.next()) {
				NutrientInfo nutrientInfo = new NutrientInfo();

				nutrientInfo.setNutId( rs.getInt("nutId") );
				nutrientInfo.setNutContent( rs.getString("nutContent") );

				//리스트에 결과값 저장
				symptomList.add(nutrientInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return symptomList;
	}

	@Override
	public int updateProductInfo(Connection conn, ProductInfo productInfo) {
		
		String sql = "";
		sql += "UPDATE productInfo SET";
		sql += " type = ?, image = ? , purchaselink = ? , allergyinfo = ?";
		sql += " WHERE productid = ?";
		int res = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, productInfo.getType());
			ps.setString(2, productInfo.getImage());
			ps.setString(3, productInfo.getPurchaseLink());
			ps.setString(4, productInfo.getAllergyInfo());
			ps.setLong(5, productInfo.getProductId());

			res = ps.executeUpdate();



		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(ps);
		}
		return res;
	}

	@Override
	public int updateTargetInfo(Connection conn, TargetInfo targetInfo) {
		String sql = "";
		sql += "UPDATE TargetInfo SET";
		sql += " targetid = ?";
		sql += " WHERE productid = ?";
		int res = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, targetInfo.getTargetId());
			ps.setLong(2, targetInfo.getProductId());

			res = ps.executeUpdate();



		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(ps);
		}
		return res;
	}

	@Override
	public int updateSymptomInfo(Connection conn, List<SymptomInfo> symptomInfo1) {
		String sql = "";
		sql += "UPDATE SymptomInfo SET";
		sql += " SymptomId = ?";
		sql += " WHERE productid = ?";

		int res = 0;

		for(int i = 0; i<symptomInfo1.size(); i++) {
			try {

				ps = conn.prepareStatement(sql); //SQL수행 객체
				ps.setInt(1, symptomInfo1.get(i).getSymptomId());
				ps.setLong(2, symptomInfo1.get(i).getProductId());

				res = ps.executeUpdate(); //SQL 수행 및 결과집합 저장


			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				//DB객체 닫기
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}
		}
		//최종 결과 반환
		return res;
	}


	@Override
	public int deleteProductTargetInfo(Connection conn, long productId) {
		
		//SQL 작성
		
			String sql1 = "";
			sql1 += "DELETE FROM targetinfo";
			sql1 += " WHERE productId = ?";
			
			int res1 = 0;

			try {
				ps = conn.prepareStatement(sql1);
				ps.setLong(1, productId);

				res1 = ps.executeUpdate();


			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				JDBCTemplate.close(ps);
			}
			
		return res1;
	}


	@Override
	public int deleteProductSymptomInfo(Connection conn, long productId) {
		//SQL 작성
		
		String sql2 = "";
		sql2 += "DELETE FROM symptomInfo";
		sql2 += " WHERE productId = ?";
		
		int res2 = 0;

		try {
			ps = conn.prepareStatement(sql2);
			ps.setLong(1, productId);

			res2 = ps.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(ps);
		}
		
	return res2;
	}

	@Override
	public int deleteProductNutrientInfo(Connection conn, long productId) {
		//SQL 작성
		
				String sql3 = "";
				sql3 += "DELETE FROM nutrientInfo";
				sql3 += " WHERE productId = ?";
				
				int res3 = 0;

				try {
					ps = conn.prepareStatement(sql3);
					ps.setLong(1, productId);

					res3 = ps.executeUpdate();


				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					JDBCTemplate.close(ps);
				}
				
			return res3;
	}
	
	
	@Override
	public int deleteProductCompBasket(Connection conn, long productId) {
		//SQL 작성
		
		String sql4 = "";
		sql4 += "DELETE FROM compBasket";
		sql4 += " WHERE productId = ?";
		
		int res4 = 0;

		try {
			ps = conn.prepareStatement(sql4);
			ps.setLong(1, productId);

			res4 = ps.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(ps);
		}
		
	return res4;
	}

	@Override
	public int deleteProductInfo(Connection conn, long productId) {
		
		String sql4 = "";
		sql4 += "DELETE FROM productInfo";
		sql4 += " WHERE productId = ?";
		
		int res4 = 0;
		
		try {
			ps = conn.prepareStatement(sql4);
			ps.setLong(1, productId);
			
			res4 = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(ps);
		}
		
		return res4;
	}

}
