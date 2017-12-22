package co.com.ies.web.rest;

import co.com.ies.GamestransactionApp;

import co.com.ies.domain.PlayRequest;
import co.com.ies.repository.PlayRequestRepository;
import co.com.ies.service.PlayRequestService;
import co.com.ies.service.dto.PlayRequestDTO;
import co.com.ies.service.mapper.PlayRequestMapper;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static co.com.ies.web.rest.TestUtil.sameInstant;
import static co.com.ies.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import co.com.ies.domain.enumeration.PlayType;
/**
 * Test class for the PlayRequestResource REST controller.
 *
 * @see PlayRequestResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GamestransactionApp.class)
public class PlayRequestResourceIntTest {

    private static final PlayType DEFAULT_PLAYTYPE = PlayType.BET;
    private static final PlayType UPDATED_PLAYTYPE = PlayType.WIN;

    private static final String DEFAULT_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_TOKEN = "BBBBBBBBBB";

    private static final String DEFAULT_SYSTEM = "AAAAAAAAAA";
    private static final String UPDATED_SYSTEM = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TIMESTAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TIMESTAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_TICKETID = "AAAAAAAAAA";
    private static final String UPDATED_TICKETID = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTIONID = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTIONID = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BALANCE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BONUSBALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BONUSBALANCE = new BigDecimal(2);

    private static final String DEFAULT_USER = "AAAAAAAAAA";
    private static final String UPDATED_USER = "BBBBBBBBBB";

    private static final String DEFAULT_OPERATOR = "AAAAAAAAAA";
    private static final String UPDATED_OPERATOR = "BBBBBBBBBB";

    @Autowired
    private PlayRequestRepository playRequestRepository;

    @Autowired
    private PlayRequestMapper playRequestMapper;

    @Autowired
    private PlayRequestService playRequestService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPlayRequestMockMvc;

    private PlayRequest playRequest;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlayRequestResource playRequestResource = new PlayRequestResource(playRequestService);
        this.restPlayRequestMockMvc = MockMvcBuilders.standaloneSetup(playRequestResource)
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
    public static PlayRequest createEntity(EntityManager em) {
        PlayRequest playRequest = new PlayRequest()
            .playtype(DEFAULT_PLAYTYPE)
            .token(DEFAULT_TOKEN)
            .system(DEFAULT_SYSTEM)
            .timestamp(DEFAULT_TIMESTAMP)
            .ticketid(DEFAULT_TICKETID)
            .transactionid(DEFAULT_TRANSACTIONID)
            .amount(DEFAULT_AMOUNT)
            .balance(DEFAULT_BALANCE)
            .bonusbalance(DEFAULT_BONUSBALANCE)
            .user(DEFAULT_USER)
            .operator(DEFAULT_OPERATOR);
        return playRequest;
    }

    @Before
    public void initTest() {
        playRequest = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlayRequest() throws Exception {
        int databaseSizeBeforeCreate = playRequestRepository.findAll().size();

        // Create the PlayRequest
        PlayRequestDTO playRequestDTO = playRequestMapper.toDto(playRequest);
        restPlayRequestMockMvc.perform(post("/api/play-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(playRequestDTO)))
            .andExpect(status().isCreated());

        // Validate the PlayRequest in the database
        List<PlayRequest> playRequestList = playRequestRepository.findAll();
        assertThat(playRequestList).hasSize(databaseSizeBeforeCreate + 1);
        PlayRequest testPlayRequest = playRequestList.get(playRequestList.size() - 1);
        assertThat(testPlayRequest.getPlaytype()).isEqualTo(DEFAULT_PLAYTYPE);
        assertThat(testPlayRequest.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testPlayRequest.getSystem()).isEqualTo(DEFAULT_SYSTEM);
        assertThat(testPlayRequest.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testPlayRequest.getTicketid()).isEqualTo(DEFAULT_TICKETID);
        assertThat(testPlayRequest.getTransactionid()).isEqualTo(DEFAULT_TRANSACTIONID);
        assertThat(testPlayRequest.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testPlayRequest.getBalance()).isEqualTo(DEFAULT_BALANCE);
        assertThat(testPlayRequest.getBonusbalance()).isEqualTo(DEFAULT_BONUSBALANCE);
        assertThat(testPlayRequest.getUser()).isEqualTo(DEFAULT_USER);
        assertThat(testPlayRequest.getOperator()).isEqualTo(DEFAULT_OPERATOR);
    }

    @Test
    @Transactional
    public void createPlayRequestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = playRequestRepository.findAll().size();

        // Create the PlayRequest with an existing ID
        playRequest.setId(1L);
        PlayRequestDTO playRequestDTO = playRequestMapper.toDto(playRequest);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlayRequestMockMvc.perform(post("/api/play-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(playRequestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlayRequest in the database
        List<PlayRequest> playRequestList = playRequestRepository.findAll();
        assertThat(playRequestList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPlaytypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = playRequestRepository.findAll().size();
        // set the field null
        playRequest.setPlaytype(null);

        // Create the PlayRequest, which fails.
        PlayRequestDTO playRequestDTO = playRequestMapper.toDto(playRequest);

        restPlayRequestMockMvc.perform(post("/api/play-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(playRequestDTO)))
            .andExpect(status().isBadRequest());

        List<PlayRequest> playRequestList = playRequestRepository.findAll();
        assertThat(playRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTokenIsRequired() throws Exception {
        int databaseSizeBeforeTest = playRequestRepository.findAll().size();
        // set the field null
        playRequest.setToken(null);

        // Create the PlayRequest, which fails.
        PlayRequestDTO playRequestDTO = playRequestMapper.toDto(playRequest);

        restPlayRequestMockMvc.perform(post("/api/play-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(playRequestDTO)))
            .andExpect(status().isBadRequest());

        List<PlayRequest> playRequestList = playRequestRepository.findAll();
        assertThat(playRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSystemIsRequired() throws Exception {
        int databaseSizeBeforeTest = playRequestRepository.findAll().size();
        // set the field null
        playRequest.setSystem(null);

        // Create the PlayRequest, which fails.
        PlayRequestDTO playRequestDTO = playRequestMapper.toDto(playRequest);

        restPlayRequestMockMvc.perform(post("/api/play-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(playRequestDTO)))
            .andExpect(status().isBadRequest());

        List<PlayRequest> playRequestList = playRequestRepository.findAll();
        assertThat(playRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTimestampIsRequired() throws Exception {
        int databaseSizeBeforeTest = playRequestRepository.findAll().size();
        // set the field null
        playRequest.setTimestamp(null);

        // Create the PlayRequest, which fails.
        PlayRequestDTO playRequestDTO = playRequestMapper.toDto(playRequest);

        restPlayRequestMockMvc.perform(post("/api/play-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(playRequestDTO)))
            .andExpect(status().isBadRequest());

        List<PlayRequest> playRequestList = playRequestRepository.findAll();
        assertThat(playRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = playRequestRepository.findAll().size();
        // set the field null
        playRequest.setUser(null);

        // Create the PlayRequest, which fails.
        PlayRequestDTO playRequestDTO = playRequestMapper.toDto(playRequest);

        restPlayRequestMockMvc.perform(post("/api/play-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(playRequestDTO)))
            .andExpect(status().isBadRequest());

        List<PlayRequest> playRequestList = playRequestRepository.findAll();
        assertThat(playRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOperatorIsRequired() throws Exception {
        int databaseSizeBeforeTest = playRequestRepository.findAll().size();
        // set the field null
        playRequest.setOperator(null);

        // Create the PlayRequest, which fails.
        PlayRequestDTO playRequestDTO = playRequestMapper.toDto(playRequest);

        restPlayRequestMockMvc.perform(post("/api/play-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(playRequestDTO)))
            .andExpect(status().isBadRequest());

        List<PlayRequest> playRequestList = playRequestRepository.findAll();
        assertThat(playRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlayRequests() throws Exception {
        // Initialize the database
        playRequestRepository.saveAndFlush(playRequest);

        // Get all the playRequestList
        restPlayRequestMockMvc.perform(get("/api/play-requests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(playRequest.getId().intValue())))
            .andExpect(jsonPath("$.[*].playtype").value(hasItem(DEFAULT_PLAYTYPE.toString())))
            .andExpect(jsonPath("$.[*].token").value(hasItem(DEFAULT_TOKEN.toString())))
            .andExpect(jsonPath("$.[*].system").value(hasItem(DEFAULT_SYSTEM.toString())))
            .andExpect(jsonPath("$.[*].timestamp").value(hasItem(sameInstant(DEFAULT_TIMESTAMP))))
            .andExpect(jsonPath("$.[*].ticketid").value(hasItem(DEFAULT_TICKETID.toString())))
            .andExpect(jsonPath("$.[*].transactionid").value(hasItem(DEFAULT_TRANSACTIONID.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].balance").value(hasItem(DEFAULT_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].bonusbalance").value(hasItem(DEFAULT_BONUSBALANCE.intValue())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER.toString())))
            .andExpect(jsonPath("$.[*].operator").value(hasItem(DEFAULT_OPERATOR.toString())));
    }

    @Test
    @Transactional
    public void getPlayRequest() throws Exception {
        // Initialize the database
        playRequestRepository.saveAndFlush(playRequest);

        // Get the playRequest
        restPlayRequestMockMvc.perform(get("/api/play-requests/{id}", playRequest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(playRequest.getId().intValue()))
            .andExpect(jsonPath("$.playtype").value(DEFAULT_PLAYTYPE.toString()))
            .andExpect(jsonPath("$.token").value(DEFAULT_TOKEN.toString()))
            .andExpect(jsonPath("$.system").value(DEFAULT_SYSTEM.toString()))
            .andExpect(jsonPath("$.timestamp").value(sameInstant(DEFAULT_TIMESTAMP)))
            .andExpect(jsonPath("$.ticketid").value(DEFAULT_TICKETID.toString()))
            .andExpect(jsonPath("$.transactionid").value(DEFAULT_TRANSACTIONID.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.balance").value(DEFAULT_BALANCE.intValue()))
            .andExpect(jsonPath("$.bonusbalance").value(DEFAULT_BONUSBALANCE.intValue()))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER.toString()))
            .andExpect(jsonPath("$.operator").value(DEFAULT_OPERATOR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPlayRequest() throws Exception {
        // Get the playRequest
        restPlayRequestMockMvc.perform(get("/api/play-requests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlayRequest() throws Exception {
        // Initialize the database
        playRequestRepository.saveAndFlush(playRequest);
        int databaseSizeBeforeUpdate = playRequestRepository.findAll().size();

        // Update the playRequest
        PlayRequest updatedPlayRequest = playRequestRepository.findOne(playRequest.getId());
        // Disconnect from session so that the updates on updatedPlayRequest are not directly saved in db
        em.detach(updatedPlayRequest);
        updatedPlayRequest
            .playtype(UPDATED_PLAYTYPE)
            .token(UPDATED_TOKEN)
            .system(UPDATED_SYSTEM)
            .timestamp(UPDATED_TIMESTAMP)
            .ticketid(UPDATED_TICKETID)
            .transactionid(UPDATED_TRANSACTIONID)
            .amount(UPDATED_AMOUNT)
            .balance(UPDATED_BALANCE)
            .bonusbalance(UPDATED_BONUSBALANCE)
            .user(UPDATED_USER)
            .operator(UPDATED_OPERATOR);
        PlayRequestDTO playRequestDTO = playRequestMapper.toDto(updatedPlayRequest);

        restPlayRequestMockMvc.perform(put("/api/play-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(playRequestDTO)))
            .andExpect(status().isOk());

        // Validate the PlayRequest in the database
        List<PlayRequest> playRequestList = playRequestRepository.findAll();
        assertThat(playRequestList).hasSize(databaseSizeBeforeUpdate);
        PlayRequest testPlayRequest = playRequestList.get(playRequestList.size() - 1);
        assertThat(testPlayRequest.getPlaytype()).isEqualTo(UPDATED_PLAYTYPE);
        assertThat(testPlayRequest.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testPlayRequest.getSystem()).isEqualTo(UPDATED_SYSTEM);
        assertThat(testPlayRequest.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testPlayRequest.getTicketid()).isEqualTo(UPDATED_TICKETID);
        assertThat(testPlayRequest.getTransactionid()).isEqualTo(UPDATED_TRANSACTIONID);
        assertThat(testPlayRequest.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testPlayRequest.getBalance()).isEqualTo(UPDATED_BALANCE);
        assertThat(testPlayRequest.getBonusbalance()).isEqualTo(UPDATED_BONUSBALANCE);
        assertThat(testPlayRequest.getUser()).isEqualTo(UPDATED_USER);
        assertThat(testPlayRequest.getOperator()).isEqualTo(UPDATED_OPERATOR);
    }

    @Test
    @Transactional
    public void updateNonExistingPlayRequest() throws Exception {
        int databaseSizeBeforeUpdate = playRequestRepository.findAll().size();

        // Create the PlayRequest
        PlayRequestDTO playRequestDTO = playRequestMapper.toDto(playRequest);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPlayRequestMockMvc.perform(put("/api/play-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(playRequestDTO)))
            .andExpect(status().isCreated());

        // Validate the PlayRequest in the database
        List<PlayRequest> playRequestList = playRequestRepository.findAll();
        assertThat(playRequestList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePlayRequest() throws Exception {
        // Initialize the database
        playRequestRepository.saveAndFlush(playRequest);
        int databaseSizeBeforeDelete = playRequestRepository.findAll().size();

        // Get the playRequest
        restPlayRequestMockMvc.perform(delete("/api/play-requests/{id}", playRequest.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PlayRequest> playRequestList = playRequestRepository.findAll();
        assertThat(playRequestList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlayRequest.class);
        PlayRequest playRequest1 = new PlayRequest();
        playRequest1.setId(1L);
        PlayRequest playRequest2 = new PlayRequest();
        playRequest2.setId(playRequest1.getId());
        assertThat(playRequest1).isEqualTo(playRequest2);
        playRequest2.setId(2L);
        assertThat(playRequest1).isNotEqualTo(playRequest2);
        playRequest1.setId(null);
        assertThat(playRequest1).isNotEqualTo(playRequest2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlayRequestDTO.class);
        PlayRequestDTO playRequestDTO1 = new PlayRequestDTO();
        playRequestDTO1.setId(1L);
        PlayRequestDTO playRequestDTO2 = new PlayRequestDTO();
        assertThat(playRequestDTO1).isNotEqualTo(playRequestDTO2);
        playRequestDTO2.setId(playRequestDTO1.getId());
        assertThat(playRequestDTO1).isEqualTo(playRequestDTO2);
        playRequestDTO2.setId(2L);
        assertThat(playRequestDTO1).isNotEqualTo(playRequestDTO2);
        playRequestDTO1.setId(null);
        assertThat(playRequestDTO1).isNotEqualTo(playRequestDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(playRequestMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(playRequestMapper.fromId(null)).isNull();
    }
}
