package co.com.ies.web.rest;

import co.com.ies.GamestransactionApp;

import co.com.ies.domain.Creditos;
import co.com.ies.repository.CreditosRepository;
import co.com.ies.service.CreditosService;
import co.com.ies.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static co.com.ies.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CreditosResource REST controller.
 *
 * @see CreditosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GamestransactionApp.class)
public class CreditosResourceIntTest {

    private static final BigDecimal DEFAULT_COIN_IN = new BigDecimal(0);
    private static final BigDecimal UPDATED_COIN_IN = new BigDecimal(1);

    private static final BigDecimal DEFAULT_COIN_OUT = new BigDecimal(0);
    private static final BigDecimal UPDATED_COIN_OUT = new BigDecimal(1);

    private static final BigDecimal DEFAULT_HANDPAY = new BigDecimal(0);
    private static final BigDecimal UPDATED_HANDPAY = new BigDecimal(1);

    private static final BigDecimal DEFAULT_JACKPOT = new BigDecimal(0);
    private static final BigDecimal UPDATED_JACKPOT = new BigDecimal(1);

    private static final BigDecimal DEFAULT_BILL = new BigDecimal(0);
    private static final BigDecimal UPDATED_BILL = new BigDecimal(1);

    @Autowired
    private CreditosRepository creditosRepository;

    @Autowired
    private CreditosService creditosService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCreditosMockMvc;

    private Creditos creditos;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CreditosResource creditosResource = new CreditosResource(creditosService);
        this.restCreditosMockMvc = MockMvcBuilders.standaloneSetup(creditosResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Creditos createEntity(EntityManager em) {
        Creditos creditos = new Creditos()
            .coinIn(DEFAULT_COIN_IN)
            .coinOut(DEFAULT_COIN_OUT)
            .handpay(DEFAULT_HANDPAY)
            .jackpot(DEFAULT_JACKPOT)
            .bill(DEFAULT_BILL);
        return creditos;
    }

    @Before
    public void initTest() {
        creditos = createEntity(em);
    }

    @Test
    @Transactional
    public void createCreditos() throws Exception {
        int databaseSizeBeforeCreate = creditosRepository.findAll().size();

        // Create the Creditos
        restCreditosMockMvc.perform(post("/api/creditos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(creditos)))
            .andExpect(status().isCreated());

        // Validate the Creditos in the database
        List<Creditos> creditosList = creditosRepository.findAll();
        assertThat(creditosList).hasSize(databaseSizeBeforeCreate + 1);
        Creditos testCreditos = creditosList.get(creditosList.size() - 1);
        assertThat(testCreditos.getCoinIn()).isEqualTo(DEFAULT_COIN_IN);
        assertThat(testCreditos.getCoinOut()).isEqualTo(DEFAULT_COIN_OUT);
        assertThat(testCreditos.getHandpay()).isEqualTo(DEFAULT_HANDPAY);
        assertThat(testCreditos.getJackpot()).isEqualTo(DEFAULT_JACKPOT);
        assertThat(testCreditos.getBill()).isEqualTo(DEFAULT_BILL);
    }

    @Test
    @Transactional
    public void createCreditosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = creditosRepository.findAll().size();

        // Create the Creditos with an existing ID
        creditos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreditosMockMvc.perform(post("/api/creditos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(creditos)))
            .andExpect(status().isBadRequest());

        // Validate the Creditos in the database
        List<Creditos> creditosList = creditosRepository.findAll();
        assertThat(creditosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCoinInIsRequired() throws Exception {
        int databaseSizeBeforeTest = creditosRepository.findAll().size();
        // set the field null
        creditos.setCoinIn(null);

        // Create the Creditos, which fails.

        restCreditosMockMvc.perform(post("/api/creditos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(creditos)))
            .andExpect(status().isBadRequest());

        List<Creditos> creditosList = creditosRepository.findAll();
        assertThat(creditosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoinOutIsRequired() throws Exception {
        int databaseSizeBeforeTest = creditosRepository.findAll().size();
        // set the field null
        creditos.setCoinOut(null);

        // Create the Creditos, which fails.

        restCreditosMockMvc.perform(post("/api/creditos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(creditos)))
            .andExpect(status().isBadRequest());

        List<Creditos> creditosList = creditosRepository.findAll();
        assertThat(creditosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHandpayIsRequired() throws Exception {
        int databaseSizeBeforeTest = creditosRepository.findAll().size();
        // set the field null
        creditos.setHandpay(null);

        // Create the Creditos, which fails.

        restCreditosMockMvc.perform(post("/api/creditos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(creditos)))
            .andExpect(status().isBadRequest());

        List<Creditos> creditosList = creditosRepository.findAll();
        assertThat(creditosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkJackpotIsRequired() throws Exception {
        int databaseSizeBeforeTest = creditosRepository.findAll().size();
        // set the field null
        creditos.setJackpot(null);

        // Create the Creditos, which fails.

        restCreditosMockMvc.perform(post("/api/creditos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(creditos)))
            .andExpect(status().isBadRequest());

        List<Creditos> creditosList = creditosRepository.findAll();
        assertThat(creditosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBillIsRequired() throws Exception {
        int databaseSizeBeforeTest = creditosRepository.findAll().size();
        // set the field null
        creditos.setBill(null);

        // Create the Creditos, which fails.

        restCreditosMockMvc.perform(post("/api/creditos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(creditos)))
            .andExpect(status().isBadRequest());

        List<Creditos> creditosList = creditosRepository.findAll();
        assertThat(creditosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCreditos() throws Exception {
        // Initialize the database
        creditosRepository.saveAndFlush(creditos);

        // Get all the creditosList
        restCreditosMockMvc.perform(get("/api/creditos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(creditos.getId().intValue())))
            .andExpect(jsonPath("$.[*].coinIn").value(hasItem(DEFAULT_COIN_IN.intValue())))
            .andExpect(jsonPath("$.[*].coinOut").value(hasItem(DEFAULT_COIN_OUT.intValue())))
            .andExpect(jsonPath("$.[*].handpay").value(hasItem(DEFAULT_HANDPAY.intValue())))
            .andExpect(jsonPath("$.[*].jackpot").value(hasItem(DEFAULT_JACKPOT.intValue())))
            .andExpect(jsonPath("$.[*].bill").value(hasItem(DEFAULT_BILL.intValue())));
    }

    @Test
    @Transactional
    public void getCreditos() throws Exception {
        // Initialize the database
        creditosRepository.saveAndFlush(creditos);

        // Get the creditos
        restCreditosMockMvc.perform(get("/api/creditos/{id}", creditos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(creditos.getId().intValue()))
            .andExpect(jsonPath("$.coinIn").value(DEFAULT_COIN_IN.intValue()))
            .andExpect(jsonPath("$.coinOut").value(DEFAULT_COIN_OUT.intValue()))
            .andExpect(jsonPath("$.handpay").value(DEFAULT_HANDPAY.intValue()))
            .andExpect(jsonPath("$.jackpot").value(DEFAULT_JACKPOT.intValue()))
            .andExpect(jsonPath("$.bill").value(DEFAULT_BILL.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCreditos() throws Exception {
        // Get the creditos
        restCreditosMockMvc.perform(get("/api/creditos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCreditos() throws Exception {
        // Initialize the database
        creditosService.save(creditos);

        int databaseSizeBeforeUpdate = creditosRepository.findAll().size();

        // Update the creditos
        Creditos updatedCreditos = creditosRepository.findOne(creditos.getId());
        // Disconnect from session so that the updates on updatedCreditos are not directly saved in db
        em.detach(updatedCreditos);
        updatedCreditos
            .coinIn(UPDATED_COIN_IN)
            .coinOut(UPDATED_COIN_OUT)
            .handpay(UPDATED_HANDPAY)
            .jackpot(UPDATED_JACKPOT)
            .bill(UPDATED_BILL);

        restCreditosMockMvc.perform(put("/api/creditos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCreditos)))
            .andExpect(status().isOk());

        // Validate the Creditos in the database
        List<Creditos> creditosList = creditosRepository.findAll();
        assertThat(creditosList).hasSize(databaseSizeBeforeUpdate);
        Creditos testCreditos = creditosList.get(creditosList.size() - 1);
        assertThat(testCreditos.getCoinIn()).isEqualTo(UPDATED_COIN_IN);
        assertThat(testCreditos.getCoinOut()).isEqualTo(UPDATED_COIN_OUT);
        assertThat(testCreditos.getHandpay()).isEqualTo(UPDATED_HANDPAY);
        assertThat(testCreditos.getJackpot()).isEqualTo(UPDATED_JACKPOT);
        assertThat(testCreditos.getBill()).isEqualTo(UPDATED_BILL);
    }

    @Test
    @Transactional
    public void updateNonExistingCreditos() throws Exception {
        int databaseSizeBeforeUpdate = creditosRepository.findAll().size();

        // Create the Creditos

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCreditosMockMvc.perform(put("/api/creditos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(creditos)))
            .andExpect(status().isCreated());

        // Validate the Creditos in the database
        List<Creditos> creditosList = creditosRepository.findAll();
        assertThat(creditosList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCreditos() throws Exception {
        // Initialize the database
        creditosService.save(creditos);

        int databaseSizeBeforeDelete = creditosRepository.findAll().size();

        // Get the creditos
        restCreditosMockMvc.perform(delete("/api/creditos/{id}", creditos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Creditos> creditosList = creditosRepository.findAll();
        assertThat(creditosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Creditos.class);
        Creditos creditos1 = new Creditos();
        creditos1.setId(1L);
        Creditos creditos2 = new Creditos();
        creditos2.setId(creditos1.getId());
        assertThat(creditos1).isEqualTo(creditos2);
        creditos2.setId(2L);
        assertThat(creditos1).isNotEqualTo(creditos2);
        creditos1.setId(null);
        assertThat(creditos1).isNotEqualTo(creditos2);
    }
}
